INSERT INTO users (username, email, password, enabled)
VALUES
('admin', 'admin@system.com', '$2a$10$examplehashadmin', TRUE),
('moderator', 'moderator@system.com', '$2a$10$examplehashmod', TRUE),
('auditor', 'auditor@system.com', '$2a$10$examplehashaudit', TRUE),
('john_doe', 'john@email.com', '$2a$10$examplehashjohn', TRUE),
('alice_smith', 'alice@email.com', '$2a$10$examplehashalice', TRUE)
ON CONFLICT (email) DO NOTHING;