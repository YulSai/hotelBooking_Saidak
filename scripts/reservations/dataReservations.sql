/*
 TRUNCATE TABLE reservations CASCADE;
  TRUNCATE TABLE statuses CASCADE;
 */

INSERT INTO reservation_statuses (name)
VALUES
('IN_PROGRESS'),
('CONFIRMED'),
('REJECTED');

INSERT INTO reservations (user_id, room_id, reservation_date, check_in, check_out, room_price, total_cost, status_id)
VALUES
((SELECT id FROM users u WHERE u.email='antony_jeffery@einrot.com'), (SELECT id FROM rooms r WHERE r.number='101S'),
'2022-08-01  08:22:06', '2022-08-05', '2022-08-06', 100, 100, (SELECT id FROM reservation_statuses s WHERE s.name='IN_PROGRESS')),
((SELECT id FROM users u WHERE u.email='antony_jeffery@einrot.com'), (SELECT id FROM rooms r WHERE r.number='102S'),
'2022-08-01  08:22:06', '2022-08-04', '2022-08-09', 150, 750, (SELECT id FROM reservation_statuses s WHERE s.name='CONFIRMED')),
((SELECT id FROM users u WHERE u.email='rida_tran@teleworm.us'), (SELECT id FROM rooms r WHERE r.number='103S'),
'2022-08-05  08:22:06', '2022-08-08', '2022-08-10', 200, 400, (SELECT id FROM reservation_statuses s WHERE s.name='IN_PROGRESS')),
((SELECT id FROM users u WHERE u.email='rida_tran@teleworm.us'), (SELECT id FROM rooms r WHERE r.number='104S'),
'2022-08-05  08:22:06', '2022-08-08', '2022-08-10', 250, 500, (SELECT id FROM reservation_statuses s WHERE s.name='REJECTED')),
((SELECT id FROM users u WHERE u.email='sophia_mckenzie@kwontol.com'), (SELECT id FROM rooms r WHERE r.number='201C'),
'2022-08-06  08:22:06', '2022-08-11', '2022-08-12', 200, 200, (SELECT id FROM reservation_statuses s WHERE s.name='REJECTED')),
((SELECT id FROM users u WHERE u.email='sophia_mckenzie@kwontol.com'), (SELECT id FROM rooms r WHERE r.number='202C'),
'2022-08-04  08:22:06', '2022-08-10', '2022-08-13', 300, 900, (SELECT id FROM reservation_statuses s WHERE s.name='CONFIRMED')),
((SELECT id FROM users u WHERE u.email='john-paul_blake@fleckens.hu'), (SELECT id FROM rooms r WHERE r.number='203C'),
 '2022-07-29 08:22:06', '2022-08-10', '2022-08-12', 400, 800, (SELECT id FROM reservation_statuses s WHERE s.name='IN_PROGRESS')),
 ((SELECT id FROM users u WHERE u.email='nannie_crawford@dayrep.com'), (SELECT id FROM rooms r WHERE r.number='204C'),
 '2022-08-01 08:22:06', '2022-08-05', '2022-08-12', 500, 3500, (SELECT id FROM reservation_statuses s WHERE s.name='CONFIRMED'));