/*
 TRUNCATE TABLE reservations CASCADE;
  TRUNCATE TABLE statuses CASCADE;
 */

INSERT INTO reservation_statuses (name)
VALUES
('IN_PROGRESS'),
('CONFIRMED'),
('REJECTED');

INSERT INTO reservations (user_id, room_price, total_cost, status_id)
VALUES
((SELECT id FROM users u WHERE u.email='antony_jeffery@einrot.com'), 100, 100, (SELECT id FROM reservation_statuses s WHERE s.name='IN_PROGRESS')),
((SELECT id FROM users u WHERE u.email='antony_jeffery@einrot.com'), 150, 750, (SELECT id FROM reservation_statuses s WHERE s.name='CONFIRMED')),
((SELECT id FROM users u WHERE u.email='rida_tran@teleworm.us'), 200, 400, (SELECT id FROM reservation_statuses s WHERE s.name='IN_PROGRESS')),
((SELECT id FROM users u WHERE u.email='rida_tran@teleworm.us'), 250, 500, (SELECT id FROM reservation_statuses s WHERE s.name='REJECTED')),
((SELECT id FROM users u WHERE u.email='sophia_mckenzie@kwontol.com'), 200, 200, (SELECT id FROM reservation_statuses s WHERE s.name='REJECTED')),
((SELECT id FROM users u WHERE u.email='sophia_mckenzie@kwontol.com'), 300, 900, (SELECT id FROM reservation_statuses s WHERE s.name='CONFIRMED')),
((SELECT id FROM users u WHERE u.email='john-paul_blake@fleckens.hu'), 400, 800, (SELECT id FROM reservation_statuses s WHERE s.name='IN_PROGRESS')),
 ((SELECT id FROM users u WHERE u.email='nannie_crawford@dayrep.com'), 500, 3500, (SELECT id FROM reservation_statuses s WHERE s.name='CONFIRMED'));