insert into public.usr (id, activation_code, active, email, password, username)
    values (1, null, true, null, '123', 'admin'),
           (2,	null,	true,	'dmitrii.bragin90@gmail.com', '123',	'pentalgin'),
           (3, null, true, 'kotelnikovartem@mail.ru', '123', 'Фысойц'),
           (4, null, true, 'stripdancer@mail.ru', '123', 'Nornarina'),
           (5, null, true, 'valerasura4@gmail.com', '123', 'Ashot');


insert into public.user_role(user_id, roles)
    values (1, 'USER'),
           (1, 'ADMIN'),
           (2, 'USER'),
           (3, 'USER'),
           (4, 'USER'),
           (5, 'USER');

insert into public.quote (id, filename, tag, text, user_id)
    values
        (2,	null,	'Тараскин',	'"Главное рулем не крути"',	2),
        (3,	null,	'Брагин',	'"Всегда заставал его в настроение игривое распиздяйство"',	2),
        (4,	null,	'Волк',	'"Жизнь не станет нормальной"',	2),
        (5,	null,	'Ирина',	'"Она туда поехала детей выращивать"',	2),
        (6,	null,	'Женя',	'"Когда ей грустно и одиноко, она достаёт из-за торшера..."',	4),
        (7,	null,	'Брагин',	'"Логичные вещи"',	5),
        (8,	null,	'Дамир-пломбир',	'"Кирпичный стеноклад"',	5);
