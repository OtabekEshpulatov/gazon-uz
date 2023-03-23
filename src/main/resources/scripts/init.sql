insert into role (code, name)
values ('ADMIN', 'Admin'),
       ('PITCH_OWNER', 'Pitch Owner'),
       ('USER', 'User');

insert into permission(code, name)
values ('MANAGE_ADMINS', 'Manage Admins'),
       ('MANAGE_PERMISSION', 'Manage Permissions'),
       ('ADD_PERMISSION', 'Add Permissions')