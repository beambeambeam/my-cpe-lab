-- Grant privileges to create databases for cpe241_user
GRANT CREATE ON *.* TO 'cpe241_user'@'%';
FLUSH PRIVILEGES;
