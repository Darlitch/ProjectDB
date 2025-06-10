-- Удаление триггера
DROP TRIGGER IF EXISTS check_test_sequence_trigger ON tests;

-- Удаление функции
DROP FUNCTION IF EXISTS check_test_sequence(); 