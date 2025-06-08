-- Создание enum типов
-- DO $$
-- BEGIN
--     IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'employee_category') THEN
--         CREATE TYPE employee_category AS ENUM ('WORKER', 'ENGINEER');
--     END IF;
--
--     IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'test_result') THEN
--         CREATE TYPE test_result AS ENUM ('SUCCESS', 'FAILED', 'IN_PROGRESS');
--     END IF;
-- END $$;

-- Создание таблицы должностей
CREATE TABLE IF NOT EXISTS positions (
    id SERIAL PRIMARY KEY,
    category int NOT NULL
);

-- Создание таблицы сотрудников
CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position_id INTEGER REFERENCES positions(id) NOT NULL,
    hire_date DATE NOT NULL
);

-- Создание таблицы цехов
CREATE TABLE IF NOT EXISTS workshops (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    director_id INTEGER REFERENCES employees(id) ON DELETE SET NULL UNIQUE
);

-- Создание таблицы участков
CREATE TABLE IF NOT EXISTS sections (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    workshop_id INTEGER REFERENCES workshops(id) ON DELETE CASCADE NOT NULL,
    head_id INTEGER REFERENCES employees(id) ON DELETE SET NULL UNIQUE
);

-- Создание таблицы мастеров участков
CREATE TABLE IF NOT EXISTS section_masters (
    section_id INTEGER REFERENCES sections(id) ON DELETE CASCADE,
    employee_id INTEGER REFERENCES employees(id) ON DELETE CASCADE,
    PRIMARY KEY (section_id, employee_id),
    CONSTRAINT unique_master_per_employee UNIQUE (employee_id)
);

-- Создание таблицы бригад
CREATE TABLE IF NOT EXISTS brigades (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    section_id INTEGER REFERENCES sections(id) ON DELETE CASCADE NOT NULL
);

-- Создание таблицы рабочих
CREATE TABLE IF NOT EXISTS workers (
    employee_id INTEGER REFERENCES employees(id) ON DELETE CASCADE,
    brigade_id INTEGER REFERENCES brigades(id) ON DELETE CASCADE,
    is_foreman BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (employee_id, brigade_id)
);

-- Создание таблицы категорий изделий
CREATE TABLE IF NOT EXISTS product_categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Создание таблицы типов изделий
CREATE TABLE IF NOT EXISTS product_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category_id INTEGER REFERENCES product_categories(id) NOT NULL
);

-- Создание таблицы изделий
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    serial_num VARCHAR(50) NOT NULL UNIQUE,
    type_id INTEGER REFERENCES product_types(id) ON DELETE RESTRICT NOT NULL,
    workshop_id INTEGER REFERENCES workshops(id) ON DELETE RESTRICT NOT NULL
);

-- Создание таблицы атрибутов изделий
CREATE TABLE IF NOT EXISTS product_attributes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category_id INTEGER REFERENCES product_categories(id) NOT NULL
);

-- Создание таблицы значений атрибутов
CREATE TABLE IF NOT EXISTS attribute_values (
    id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES products(id) ON DELETE CASCADE NOT NULL,
    attribute_id INTEGER REFERENCES product_attributes(id) ON DELETE RESTRICT NOT NULL,
    value VARCHAR(255) NOT NULL,
    UNIQUE(product_id, attribute_id)
);

-- Создание таблицы работ по сборке
CREATE TABLE IF NOT EXISTS assembly_jobs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    section_id INTEGER REFERENCES sections(id) NOT NULL
);

-- Создание таблицы производственного процесса
CREATE TABLE IF NOT EXISTS production_process (
    id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES products(id) ON DELETE CASCADE NOT NULL,
    job_id INTEGER REFERENCES assembly_jobs(id) ON DELETE RESTRICT NOT NULL,
    brigade_id INTEGER REFERENCES brigades(id) ON DELETE RESTRICT NOT NULL,
    start_date DATE NOT NULL CHECK (start_date <= CURRENT_DATE),
    end_date DATE,
    CONSTRAINT valid_dates CHECK (end_date IS NULL OR end_date >= start_date)
);

-- Создание таблицы испытательных лабораторий
CREATE TABLE IF NOT EXISTS test_labs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Создание таблицы оборудования
CREATE TABLE IF NOT EXISTS equipment (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    lab_id INTEGER REFERENCES test_labs(id) NOT NULL
);

-- Создание таблицы испытаний
CREATE TABLE IF NOT EXISTS tests (
    id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES products(id) ON DELETE CASCADE NOT NULL,
    lab_id INTEGER REFERENCES test_labs(id) ON DELETE RESTRICT NOT NULL,
    result int NOT NULL,
    start_date DATE NOT NULL CHECK (start_date <= CURRENT_DATE),
    end_date DATE,
    CONSTRAINT valid_test_dates CHECK (end_date IS NULL OR end_date >= start_date)
);

-- Создание таблицы испытателей
CREATE TABLE IF NOT EXISTS testers (
    test_id INTEGER REFERENCES tests(id) ON DELETE CASCADE,
    employee_id INTEGER REFERENCES employees(id) ON DELETE CASCADE,
    PRIMARY KEY (test_id, employee_id)
);

-- Создание таблицы используемого оборудования
CREATE TABLE IF NOT EXISTS test_equipment (
    test_id INTEGER REFERENCES tests(id) ON DELETE CASCADE,
    equipment_id INTEGER REFERENCES equipment(id) ON DELETE RESTRICT,
    PRIMARY KEY (test_id, equipment_id)
);

-- Создание индексов для оптимизации запросов
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_products_type') THEN
        CREATE INDEX idx_products_type ON products(type_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_products_workshop') THEN
        CREATE INDEX idx_products_workshop ON products(workshop_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_production_process_product') THEN
        CREATE INDEX idx_production_process_product ON production_process(product_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_production_process_brigade') THEN
        CREATE INDEX idx_production_process_brigade ON production_process(brigade_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_tests_product') THEN
        CREATE INDEX idx_tests_product ON tests(product_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_tests_lab') THEN
        CREATE INDEX idx_tests_lab ON tests(lab_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_employees_position') THEN
        CREATE INDEX idx_employees_position ON employees(position_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_workers_brigade') THEN
        CREATE INDEX idx_workers_brigade ON workers(brigade_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_section_masters_section') THEN
        CREATE INDEX idx_section_masters_section ON section_masters(section_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_section_masters_employee') THEN
        CREATE INDEX idx_section_masters_employee ON section_masters(employee_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_production_process_job') THEN
        CREATE INDEX idx_production_process_job ON production_process(job_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_testers_test_employee') THEN
        CREATE INDEX idx_testers_test_employee ON testers(test_id, employee_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'idx_unique_foreman_per_brigade') THEN
        CREATE UNIQUE INDEX idx_unique_foreman_per_brigade ON workers(brigade_id) WHERE is_foreman = TRUE;
    END IF;
END $$;


-- Добавление комментариев к таблицам для документации
COMMENT ON TABLE workers IS 'Сотрудники, которые являются рабочими. Бригадиры выбираются из числа рабочих.';
COMMENT ON TABLE section_masters IS 'Мастера участков. Назначаются из числа инженерно-технического персонала.';
COMMENT ON TABLE workshops IS 'Цеха предприятия. Директора назначаются из числа инженерно-технического персонала. В цехе может быть только один директор.';
COMMENT ON TABLE sections IS 'Участки предприятия. Начальники назначаются из числа инженерно-технического персонала. На участке может быть только один начальник.';
COMMENT ON TABLE testers IS 'Испытатели. Назначаются из числа инженерно-технического персонала.';

-- Добавление комментариев к ограничениям
COMMENT ON CONSTRAINT valid_dates ON production_process IS 'Дата окончания должна быть позже даты начала';
COMMENT ON CONSTRAINT valid_test_dates ON tests IS 'Дата окончания испытания должна быть позже даты начала';
COMMENT ON CONSTRAINT unique_master_per_employee ON section_masters IS 'Гарантирует, что один сотрудник не может быть мастером нескольких участков';

-- Функции для проверки должностей
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_director_position') THEN
        CREATE FUNCTION check_director_position()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NEW.director_id IS NOT NULL AND NOT EXISTS (
                SELECT 1 
                FROM employees e
                JOIN positions p ON p.id = e.position_id
                WHERE e.id = NEW.director_id 
                AND p.category = 1
            ) THEN
                RAISE EXCEPTION 'Director must be an engineer';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_head_position') THEN
        CREATE FUNCTION check_head_position()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NEW.head_id IS NOT NULL AND NOT EXISTS (
                SELECT 1 
                FROM employees e
                JOIN positions p ON p.id = e.position_id
                WHERE e.id = NEW.head_id 
                AND p.category = 1
            ) THEN
                RAISE EXCEPTION 'Section head must be an engineer';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_master_position') THEN
        CREATE FUNCTION check_master_position()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NOT EXISTS (
                SELECT 1 
                FROM employees e
                JOIN positions p ON p.id = e.position_id
                WHERE e.id = NEW.employee_id 
                AND p.category = 1
            ) THEN
                RAISE EXCEPTION 'Master must be an engineer';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_worker_position') THEN
        CREATE FUNCTION check_worker_position()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NOT EXISTS (
                SELECT 1 
                FROM employees e
                JOIN positions p ON p.id = e.position_id
                WHERE e.id = NEW.employee_id 
                AND p.category = 0
            ) THEN
                RAISE EXCEPTION 'Worker must have worker position';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_tester_position') THEN
        CREATE FUNCTION check_tester_position()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NOT EXISTS (
                SELECT 1 
                FROM employees e
                JOIN positions p ON p.id = e.position_id
                WHERE e.id = NEW.employee_id 
                AND p.category = 1
            ) THEN
                RAISE EXCEPTION 'Tester must be an engineer';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_attribute_category') THEN
        CREATE FUNCTION check_attribute_category()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NOT EXISTS (
                SELECT 1 
                FROM products p
                JOIN product_types pt ON pt.id = p.type_id
                JOIN product_attributes pa ON pa.category_id = pt.category_id
                WHERE p.id = NEW.product_id 
                AND pa.id = NEW.attribute_id
            ) THEN
                RAISE EXCEPTION 'Attribute must belong to the same category as the product';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_test_equipment_lab') THEN
        CREATE FUNCTION check_test_equipment_lab()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NOT EXISTS (
                SELECT 1 
                FROM tests t
                JOIN equipment e ON e.lab_id = t.lab_id
                WHERE t.id = NEW.test_id 
                AND e.id = NEW.equipment_id
            ) THEN
                RAISE EXCEPTION 'Equipment must belong to the same lab as the test';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_production_sequence') THEN
        CREATE FUNCTION check_production_sequence()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF EXISTS (
                SELECT 1 
                FROM production_process pp
                WHERE pp.product_id = NEW.product_id
                AND pp.id != NEW.id
                AND (
                    (NEW.start_date BETWEEN pp.start_date AND COALESCE(pp.end_date, CURRENT_DATE))
                    OR (COALESCE(NEW.end_date, CURRENT_DATE) BETWEEN pp.start_date AND COALESCE(pp.end_date, CURRENT_DATE))
                    OR (pp.start_date BETWEEN NEW.start_date AND COALESCE(NEW.end_date, CURRENT_DATE))
                )
            ) THEN
                RAISE EXCEPTION 'Product cannot be in production at multiple sections simultaneously';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_test_sequence') THEN
        CREATE FUNCTION check_test_sequence()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF EXISTS (
                SELECT 1 
                FROM tests t
                WHERE t.product_id = NEW.product_id
                AND t.id != NEW.id
                AND (
                    (NEW.start_date BETWEEN t.start_date AND COALESCE(t.end_date, CURRENT_DATE))
                    OR (COALESCE(NEW.end_date, CURRENT_DATE) BETWEEN t.start_date AND COALESCE(t.end_date, CURRENT_DATE))
                    OR (t.start_date BETWEEN NEW.start_date AND COALESCE(NEW.end_date, CURRENT_DATE))
                )
            ) THEN
                RAISE EXCEPTION 'Product cannot be tested in multiple labs simultaneously';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_job_brigade_section') THEN
        CREATE FUNCTION check_job_brigade_section()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NOT EXISTS (
                SELECT 1 
                FROM assembly_jobs aj
                JOIN brigades b ON b.section_id = aj.section_id
                WHERE aj.id = NEW.job_id 
                AND b.id = NEW.brigade_id
            ) THEN
                RAISE EXCEPTION 'Job must belong to the same section as the brigade';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_proc WHERE proname = 'check_foreman_in_brigade') THEN
        CREATE FUNCTION check_foreman_in_brigade()
        RETURNS TRIGGER AS $BODY$
        BEGIN
            IF NEW.is_foreman = TRUE AND NOT EXISTS (
                SELECT 1 
                FROM workers w
                WHERE w.employee_id = NEW.employee_id 
                AND w.brigade_id = NEW.brigade_id
            ) THEN
                RAISE EXCEPTION 'Foreman must be a member of their brigade';
            END IF;
            RETURN NEW;
        END;
        $BODY$ LANGUAGE plpgsql;
    END IF;
END $$;

-- Триггеры для проверки должностей
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_director_position_trigger') THEN
        CREATE TRIGGER check_director_position_trigger
            BEFORE INSERT OR UPDATE ON workshops
            FOR EACH ROW
            EXECUTE FUNCTION check_director_position();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_head_position_trigger') THEN
        CREATE TRIGGER check_head_position_trigger
            BEFORE INSERT OR UPDATE ON sections
            FOR EACH ROW
            EXECUTE FUNCTION check_head_position();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_master_position_trigger') THEN
        CREATE TRIGGER check_master_position_trigger
            BEFORE INSERT OR UPDATE ON section_masters
            FOR EACH ROW
            EXECUTE FUNCTION check_master_position();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_worker_position_trigger') THEN
        CREATE TRIGGER check_worker_position_trigger
            BEFORE INSERT OR UPDATE ON workers
            FOR EACH ROW
            EXECUTE FUNCTION check_worker_position();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_tester_position_trigger') THEN
        CREATE TRIGGER check_tester_position_trigger
            BEFORE INSERT OR UPDATE ON testers
            FOR EACH ROW
            EXECUTE FUNCTION check_tester_position();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_attribute_category_trigger') THEN
        CREATE TRIGGER check_attribute_category_trigger
            BEFORE INSERT OR UPDATE ON attribute_values
            FOR EACH ROW
            EXECUTE FUNCTION check_attribute_category();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_test_equipment_lab_trigger') THEN
        CREATE TRIGGER check_test_equipment_lab_trigger
            BEFORE INSERT OR UPDATE ON test_equipment
            FOR EACH ROW
            EXECUTE FUNCTION check_test_equipment_lab();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_production_sequence_trigger') THEN
        CREATE TRIGGER check_production_sequence_trigger
            BEFORE INSERT OR UPDATE ON production_process
            FOR EACH ROW
            EXECUTE FUNCTION check_production_sequence();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_test_sequence_trigger') THEN
        CREATE TRIGGER check_test_sequence_trigger
            BEFORE INSERT OR UPDATE ON tests
            FOR EACH ROW
            EXECUTE FUNCTION check_test_sequence();
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'check_job_brigade_section_trigger') THEN
        CREATE TRIGGER check_job_brigade_section_trigger
            BEFORE INSERT OR UPDATE ON production_process
            FOR EACH ROW
            EXECUTE FUNCTION check_job_brigade_section();
    END IF;
END $$;
