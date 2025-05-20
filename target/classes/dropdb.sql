-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- Terminate connections to the database
SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'furthermvp_backend';

DROP DATABASE IF EXISTS furthermvp_backend;
