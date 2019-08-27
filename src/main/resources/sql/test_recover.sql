alter table test.person
    modify user_id int;
alter table test.person
    drop primary key;


INSERT INTO test.person (user_id, name, birthday, edu, marry, income, gender, career) VALUES (1, 'tom', '2019-08-27 15:53:34', 'graduate', 'married', 10000, 'male', 'engineer');
INSERT INTO test.person (user_id, name, birthday, edu, marry, income, gender, career) VALUES (2, 'jim', '2019-08-27 16:26:34', 'phd', 'unmarried', 20000, 'male', 'docter');
# -----------------插入---------------------
alter table test.person
    add primary key (user_id);
alter table test.person
    modify user_id int auto_increment;

# ==================下一个表=====================
alter table test.diet
    modify diet_id int;
alter table test.diet
    drop primary key;


INSERT INTO test.diet (diet_id, user_id, diet_start, diet_end, staple_kind, staple_amount, water, protein, vegetable_kind, vegetable_amount) VALUES (1, 1, '2019-08-27 16:16:21', '2019-08-27 16:16:23', 'rice', 100, 300, 30, 'potato', 200);
INSERT INTO test.diet (diet_id, user_id, diet_start, diet_end, staple_kind, staple_amount, water, protein, vegetable_kind, vegetable_amount) VALUES (2, 1, '2019-08-27 16:16:45', '2019-08-27 16:16:47', 'noddle', 200, 400, 40, 'tomato', 300);
INSERT INTO test.diet (diet_id, user_id, diet_start, diet_end, staple_kind, staple_amount, water, protein, vegetable_kind, vegetable_amount) VALUES (3, 1, '2019-08-27 16:17:54', '2019-08-27 16:17:56', 'pizza', 200, 200, 50, 'onion', 100);
# -----------------插入---------------------


alter table test.diet
    add primary key (diet_id);
alter table test.diet
    modify diet_id int auto_increment;

# ==================下一个表=====================
alter table test.training
    modify training_id int;
alter table test.training
    drop primary key;



INSERT INTO test.training (training_id, user_id, training_start, training_end, training_kind, coach, position, performance) VALUES (1, 1, '2019-08-27 16:19:11', '2019-08-27 16:19:13', 'speed', 'jim', 'outdoor', 'A+');
INSERT INTO test.training (training_id, user_id, training_start, training_end, training_kind, coach, position, performance) VALUES (2, 1, '2019-08-27 16:19:36', '2019-08-27 16:19:37', 'endurance', 'jack', 'playground', 'B');
INSERT INTO test.training (training_id, user_id, training_start, training_end, training_kind, coach, position, performance) VALUES (3, 1, '2019-08-27 16:20:59', '2019-08-27 16:21:00', 'flexibility', 'john', 'pool', 'A');
INSERT INTO test.training (training_id, user_id, training_start, training_end, training_kind, coach, position, performance) VALUES (4, 2, '2019-08-27 16:27:34', '2019-08-27 16:27:35', 'flexibility', 'jim', 'playground', 'A-');
# -----------------插入---------------------


alter table test.training
    add primary key (training_id);
alter table test.training
    modify training_id int auto_increment;

# ==================下一个表=====================
