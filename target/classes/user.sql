-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER IF NOT EXISTS further_owner
WITH PASSWORD 'furtherownerpw';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO further_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO further_owner;

CREATE USER IF NOT EXISTS further_appuser
WITH PASSWORD 'furtherapppw';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO further_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO further_appuser;
