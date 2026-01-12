<?php
// Database configuration
$db_host = getenv('DB_HOST') ?: 'db';
$db_user = getenv('DB_USER') ?: 'root';
$db_password = getenv('DB_PASSWORD') ?: 'rootpassword';
$db_name = getenv('DB_NAME') ?: 'cpe241_db';

// Test database connection
try {
    $conn = new mysqli($db_host, $db_user, $db_password, $db_name);

    if ($conn->connect_error) {
        die("Connection failed: " . $conn->connect_error);
    }

    echo "<h1>CPE241 - Database Connection Test</h1>";
    echo "<p>Database connection successful!</p>";
    echo "<p>MySQL Version: " . $conn->server_info . "</p>";
    echo "<p>Database: " . $db_name . "</p>";

    $conn->close();
} catch (Exception $e) {
    echo "<h1>CPE241 - Database Connection Error</h1>";
    echo "<p>Error: " . $e->getMessage() . "</p>";
}
?>
