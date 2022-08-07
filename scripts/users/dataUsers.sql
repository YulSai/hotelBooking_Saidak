/*
 * TRUNCATE TABLE users;
 * TRUNCATE TABLE roles;
 */

INSERT INTO roles (name)
VALUES
('ADMIN'),
('CLIENT'),
('GUEST');

INSERT INTO users (first_name, last_name, email, password, phone_number, role_id)
VALUES
('Maxim', 'Hammond', 'maxim_hammond@kwontol.com', '6FsXwnhvi655cUtAQvEC', '+48511906624', (SELECT id FROM roles c WHERE c.name = 'ADMIN')),
('Adem', 'Johnson', 'adem_johnson@armyspy.com', '2gCdVGxpsvTwVoupvNuw', '+485101085423', (SELECT id FROM roles c WHERE c.name = 'ADMIN')),
('Reese', 'Mckenzie', 'reese_mckenzie@cuvox.de', '6sxN3agX6xAQMJPfK7U8', '+375295741238', (SELECT id FROM roles c WHERE c.name = 'ADMIN')),
('Saad', 'Nielsen','saad_nielsen@dayrep.com', 'NCP8hbne5eHRmYzd4f1P', '+375335213890', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Alesha', 'Peterson', 'alesha_peterson@einrot.com', '5PpH8AJykuqCHCVjf74J', '+48458632147', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Anne-Marie', 'Leon', 'anne-marie_leon@fleckens.hu','L68gMfDJ5k5qN4dz9isb', '+38789254632', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Muneeb', 'Cousins', 'muneeb_cousins@gustr.com', 'j7se8Kcgkbv8XU7MMMH6', '+38128456208', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Carol', 'Rivas', 'carol_rivas@jourrapide.com', 'hn2CCYvmxi1AC5s2VvYF', '+48123548697', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Braiden', 'Allan', 'braiden_allan@rhyta.com', 'yyfs7HNJimB0ndDZeikq', '+34258159641', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Jaya', 'Nielsen', 'jaya_nielsenl@superrito.com', 'nbNWpXLKpV3pm9Q9HFPh', '+34208962145', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Kristian', 'Hanson', 'kristian_hanson@teleworm.us', 'bqZRfVk3ByhaMAkaJEAq', '+48124569783', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Cosmo', 'Franklin', 'cosmo_franklin@kwontol.com', '0K2ha8WXTd8RDCUeFKAm', '+375442596872', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Isa', 'Mckenzie', 'isa_mckenzie@armyspy.com', 'eRfgAuXv2xLZpp8kFhY4', '+375445236987', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Denis', 'Wormald', 'denis_wormald@cuvox.de', 'HFCtWVmzCMCs62aUvaoc', '+48145369875', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Nannie', 'Crawford', 'nannie_crawford@dayrep.com', 'qghZ4e7iJsfaCw74aXHk', '+38549782654', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Antony', 'Jeffery', 'antony_jeffery@einrot.com', '4g8mHx0kvLQeAG9Cobjp', '+375254781235', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('John-Paul', 'Blake', 'john-paul_blake@fleckens.hu', 'AyLEcCMMKLpaPv2pQXtZ', '+38547632987', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Jocelyn', 'Porter', 'jocelyn_porter@rhyta.com', '7PCmTRAyv2xbuGY3uw76', '+48215369742', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Rida', 'Tran', 'rida_tran@teleworm.us', 'UCwB3U3eYHf6pPaHExTc', '+375296543218', (SELECT id FROM roles c WHERE c.name = 'CLIENT')),
('Sophia', 'Mckenzie', 'sophia_mckenzie@kwontol.com', 'Bb1LBYdFVBJRbcqxBXiJ', '+375256982148', (SELECT id FROM roles c WHERE c.name = 'CLIENT'));