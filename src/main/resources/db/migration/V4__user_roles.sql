-- admin -> ADMIN
INSERT INTO user_roles (user_id, role_id, assigned_by)
VALUES (1, 1, 1)
ON CONFLICT DO NOTHING;

-- moderator -> MODERATOR
INSERT INTO user_roles (user_id, role_id, assigned_by)
VALUES (2, 2, 1)
ON CONFLICT DO NOTHING;

-- auditor -> AUDITOR
INSERT INTO user_roles (user_id, role_id, assigned_by)
VALUES (3, 4, 1)
ON CONFLICT DO NOTHING;

-- john_doe -> USER
INSERT INTO user_roles (user_id, role_id, assigned_by)
VALUES (4, 3, 1)
ON CONFLICT DO NOTHING;

-- alice_smith -> USER
INSERT INTO user_roles (user_id, role_id, assigned_by)
VALUES (5, 3, 1)
ON CONFLICT DO NOTHING;