INSERT INTO users (username, email, password, authority, enabled)
VALUES
('Admin', 'admin@gmail.com', '$2a$12$1k1nqYxgIuRxjugURLJlru5El6ZQtdAwd6whQgfo2MtSzSqmrmb3O',  'ROLE_ADMIN' ,true),
('Milos', 'mil@gmail.com', '{bcrypt}Password/456_encoded_here',  'ROLE_USER',true),
('Majo', 'majo@gmail.com', '{bcrypt}Password/789_encoded_here', 'ROLE_USER',true);

INSERT INTO score DEFAULT VALUES;

INSERT INTO media ( name, path, type, day_time) VALUES
('titleVideo', '/files/videos/day.mp4', 'VIDEO', 'DAY'),
('titleVideo', '/files/videos/night.mp4', 'VIDEO', 'NIGHT'),
('sectionImageA', '/files/images/1.jpg', 'IMG', 'DAY'),
('sectionImageB', '/files/images/2.jpg', 'IMG', 'DAY'),
('sectionImageA', '/files/images/1-night.jpg', 'IMG', 'NIGHT'),
('sectionImageB', '/files/images/2-night.jpg', 'IMG', 'NIGHT'),
( 'bridge', '/files/images/7.jpg', 'IMG', 'DAY'),
( 'tree', '/files/images/8.jpg', 'IMG', 'DAY'),
( 'nightForest', '/files/images/7-night.jpg', 'IMG', 'DAY'),
( 'nightSky', '/files/images/8-night.jpg', 'IMG', 'DAY');
