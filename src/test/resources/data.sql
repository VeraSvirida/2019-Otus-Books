insert into writers (id, fullname, birthday) values (4, 'Writer1', '6 июня 1799');
insert into writers (id, fullname, birthday) values (5, 'Writer2', '15 октября 1814');
insert into writers (id, fullname, birthday) values (6, 'Writer3', '9 сентября 1828');

insert into genres (id, name) values (4, 'Genre1');
insert into genres (id, name) values (5, 'Genre2');
insert into genres (id, name) values (6, 'Genre3');

insert into books (id, title, description, writer_id, genre_id) values (4, 'Title1',
'Description1',
4,4);
insert into books (id, title, description, writer_id, genre_id) values (5, 'Title2',
'Description2',
5,5);
insert into books (id, title, description, writer_id, genre_id) values (6, 'Title3',
'Description3',
6,6);
insert into comments (id, comment, book_id) values (4, 'Comment1', 4);
insert into comments (id, comment, book_id) values (5, 'Comment2', 5);
insert into comments (id, comment, book_id) values (6, 'Comment3', 4);
