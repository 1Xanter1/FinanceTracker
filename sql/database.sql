create table user(
    user_id serial8 primary key not null,
    username varchar not null,
    password varchar not null,
    email varchar not null,
    created_at timestamp default current_timestamp
);
create table category(
    category_id serial8 primary key not null,
    category_name varchar not null,
    category_type varchar check (type in ('income', 'expense')),
    user_id int references user(user_id)
);
create table transactions(
    transaction_id serial8 primary key not null,
    amount float not null,
    description varchar,
    transaction_type varchar check (('income', 'expense') ),
    user_id int references user(user_id),
    category_id int references category(category_id)
);
create table goal(
    goal_id serial8 primary key not null,
    title varchar not null,
    target_amount float not null,
    current_amount float,
    deadline date,
    user_id int references user(user_id)
);