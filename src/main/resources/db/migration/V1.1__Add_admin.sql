insert into public.usr (id, activation_code, active, email, password, username)
    values (1, null, true, null, 'Meg~7+inO', 'admin'),
           (2,	null,	true,	'dmitrii.bragin90@gmail.com', '1fWEikNBV*yxZZq',	'pentalgin'),
           (3, null, true, 'kotelnikovartem@mail.ru', 'фысойц', 'Фысойц'),
           (4, null, true, 'kotelnikovartem@mail.ru', 'хуй', 'Хуй'),
           (5, null, true, 'stripdancer@mail.ru', 'qaz123', 'Nornarina'),
           (6, null, true, 'valerasura4@gmail.com', 'ashotcumshot', 'Ashot');


insert into public.user_role(user_id, roles)
    values (1, 'USER'),
           (1, 'ADMIN'),
           (2, 'USER'),
           (3, 'USER'),
           (4, 'USER'),
           (5, 'USER'),
           (6, 'USER');

insert into public.quote (id, filename, tag, text, user_id)
    values
        (2,	null,	'Тараскин',	'"Главное рулем не крути"',	2),
        (3,	null,	'Брагин',	'"Всегда заставал его в настроение игривое распиздяйство"',	2),
        (4,	null,	'Волк',	'"Жизнь не станет нормальной"',	2),
        (5,	null,	'Артем',	'"Хули нет, если да"',	4),
        (6,	null,	'Ирина',	'"Она туда поехала детей выращивать"',	2),
        (7,	null,	'Женя',	'"Когда ей грустно и одиноко, она достаёт из-за торшера..."',	5),
        (8,	null,	'Брагин',	'"Логичные вещи"',	6),
        (9,	null,	'Дамир-пломбир',	'"Кирпичный стеноклад"',	6);
