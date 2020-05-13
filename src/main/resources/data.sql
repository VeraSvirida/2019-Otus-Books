insert into writers (id, fullname, birthday) values (1, 'Пушкин Александр Сергеевич', '6 июня 1799');
insert into writers (id, fullname, birthday) values (2, 'Лермонтов Юрий Михайлович', '15 октября 1814');
insert into writers (id, fullname, birthday) values (3, 'Толстой Лев Николаевич', '9 сентября 1828');

insert into genres (id, name) values (1, 'сказка');
insert into genres (id, name) values (2, 'поэма');
insert into genres (id, name) values (3, 'роман');

insert into books (id, title, description, writer_id, genre_id) values (1, 'Сказка о царе Салтане',
'СКАЗКА О ЦАРЕ САЛТАНЕ,О СЫНЕ ЕГО СЛАВНОМ И МОГУЧЕМ БОГАТЫРЕ КНЯЗЕ ГВИДОНЕ САЛТАНОВИЧЕ И О ПРЕКРАСНОЙ ЦАРЕВНЕ ЛЕБЕДИ',
1,1);
insert into books (id, title, description, writer_id, genre_id) values (2, 'Мцыри',
'Романтическая поэма М. Ю. Лермонтова, написанная в 1839 году и опубликованная (с цензурными пропусками) в 1840 году в единственном прижизненном издании поэта — сборнике «Стихотворения М. Лермонтова».',
2,2);
insert into books (id, title, description, writer_id, genre_id) values (3, 'Война и Мир',
'Роман-эпопея, описывающий русское общество в эпоху войн против Наполеона в 1805 — 1812 годах',
3,3);
insert into comments (id, comment, book_id) values (1, 'good', 1);
insert into comments (id, comment, book_id) values (2, 'not bad', 2);
insert into comments (id, comment, book_id) values (3, 'so so', 1);
