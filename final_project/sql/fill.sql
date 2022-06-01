INSERT INTO `users` (`login`, `password`, `role`)
VALUES
    ('admin', 'admin', 0),
    ('dispatcher', 'dispatcher', 2),
    ('user', 'user', 1),
    ('test', 'test', 1),
    ('Judy', 'admin', 0),
    ('Tess', 'user', 1),
    ('Harper', 'test', 1),
    ('Rowena', 'admin', 0);

INSERT INTO `brigades` (`name`)
VALUES
    ('Cosmos'),
    ('Volcanos'),
    ('Joker');

INSERT INTO `employees` (`id`, `position`, `id_brigade`)
VALUES
    (1, 0, 1),
    (2, 1, 1),
    (3, 1, 1),
    (4, 2, 2),
    (5, 2, 2),
    (6, 2, NULL),
    (7, 2, NULL);

INSERT INTO `flights` (
    `number_tickets`,
    `date_time_departure`,
    `date_time_arrival`,
    `departure_point`,
    `destination`,
    `status`,
    `id_brigade`)
VALUES
    (3, '2022-03-01 14:50:00', '2022-03-01 20:00:00', 'london', 'washington', 0, 1),
    (5, '2022-03-04 18:50:00', '2022-03-04 20:00:00', 'minsk', 'moscow', 1, 2);