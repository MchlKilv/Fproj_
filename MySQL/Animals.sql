CREATE SCHEMA HumanFriends;
USE HumanFriends;

/*
В соответствии с диаграммой классов таблицы будут связаны ключами. 
*/

CREATE TABLE IF NOT EXISTS Animals(  -- Первая таблица в соответствии с диаграммой классов
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Type VARCHAR(50)
);

INSERT INTO Animals(Type) VALUES  -- Заполняем значениями
	('Домашние питомцы'),
	('Вьючные животные');

CREATE TABLE IF NOT EXISTS Pets(  -- Создаем и заполняем виды животных, домашние и вьючные
	TypeId INT DEFAULT 1,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Animal VARCHAR(50),
	FOREIGN KEY (TypeId) REFERENCES Animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Pets(Animal) VALUES
	('Кот'),
	('Собака'),
	('Хомяк');

CREATE TABLE IF NOT EXISTS PackAnimals(
	TypeId INT DEFAULT 2,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Animal VARCHAR(50),
	FOREIGN KEY (TypeId) REFERENCES Animals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO PackAnimals(Animal) VALUES
	('Верблюд'),
	('Лошадь'),
	('Осел');

CREATE TABLE IF NOT EXISTS Cats(  -- Создаем и заполняем таблицы с конкретными представителями классов
	AnimalId INT DEFAULT 1,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50),
	BirthDate DATE,
	Commands TEXT,
	FOREIGN KEY (AnimalId) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Cats(Name, BirthDate, Commands) VALUES
	('Мурзик', '2016-03-11', 'лежать, мурчать, прыгать'),
	('Барсик', '2021-11-03', 'кусь, мурчать'),
	('Багира', '2023-06-22', 'кусь, мурчать, прыгать');

CREATE TABLE IF NOT EXISTS Dogs(
	AnimalId INT DEFAULT 2,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50),
	BirthDate DATE,
	Commands TEXT,
	FOREIGN KEY (AnimalId) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Dogs(Name, BirthDate, Commands) VALUES
	('Шарик', '2018-03-11', 'лежать, сидеть, лапу'),
	('Тузик', '2022-11-03', 'голос, лежать'),
	('Лайка', '2019-06-22', 'сидеть, лежать, голос');

CREATE TABLE IF NOT EXISTS Hamsters(
	AnimalId INT DEFAULT 3,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50),
	BirthDate DATE,
	Commands TEXT,
	FOREIGN KEY (AnimalId) REFERENCES Pets (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Hamsters(Name, BirthDate, Commands) VALUES
	('Пушок', '2023-03-11', 'лежать, прыгать'),
	('Малыш', '2023-11-03', 'бегать'),
	('Хомяк', '2022-06-22', 'лежать');

CREATE TABLE IF NOT EXISTS Camels(
	AnimalId INT DEFAULT 1,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50),
	BirthDate DATE,
	Commands TEXT,
	FOREIGN KEY (AnimalId) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Camels(Name, BirthDate, Commands) VALUES
	('Джафар', '2016-06-11', 'бегать, носить, плевать'),
	('Махмуд', '2023-11-03', 'бегать, ходить'),
	('Абу', '2022-06-22', 'лежать, бегать, носить');

CREATE TABLE IF NOT EXISTS Horses(
	AnimalId INT DEFAULT 2,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50),
	BirthDate DATE,
	Commands TEXT,
	FOREIGN KEY (AnimalId) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Horses(Name, BirthDate, Commands) VALUES
	('Ветер', '2018-06-11', 'прыгать, галоп, наездник'),
	('Атлет', '2023-01-03', 'упряжка, груз'),
	('Пржевальский', '2022-12-22', 'выступать, аллюр, поклон');

CREATE TABLE IF NOT EXISTS Donkeys(
	AnimalId INT DEFAULT 3,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50),
	BirthDate DATE,
	Commands TEXT,
	FOREIGN KEY (AnimalId) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Donkeys(Name, BirthDate, Commands) VALUES
	('Гектор', '2022-12-11', 'носить, бежать'),
	('Люська', '2016-06-03', 'бежать'),
	('Клаксон', '2023-08-22', 'громкий звук, бежать');

DELETE FROM Camels;  -- Удаляем все данные из таблицы верблюдов

CREATE TABLE IF NOT EXISTS AllPack( -- Создаем таблицу, где будут объединены лошади и ослы
	AnimalId INT,
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(50),
	BirthDate DATE,
	Commands TEXT,
	FOREIGN KEY (AnimalId) REFERENCES PackAnimals (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO AllPack(AnimalId, Name, BirthDate, Commands)  -- Заполняем таблицу при помощи выборки
	SELECT AnimalId, Name, BirthDate, Commands FROM Horses
	UNION
	SELECT AnimalId, Name, BirthDate, Commands FROM Donkeys;

CREATE TABLE IF NOT EXISTS Youngs( -- Создаем таблицу, куда будут помещены животные от 1 до 3 лет
	Type VARCHAR(50),
	Name VARCHAR(50),
	BirthDate DATE,
	Commands TEXT,
	Age TEXT
);

 -- Функция будет рассчитывать, записывать возраст животных
DELIMITER // 

CREATE FUNCTION CalculateAgeInMonths(birthdate DATE)
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE birthdate_month INT;
    DECLARE birthdate_year INT;
    DECLARE current_month INT;
    DECLARE current_year INT;
    DECLARE age_in_months INT;
    DECLARE age_text VARCHAR(50);

    SET birthdate_month = MONTH(birthdate);
    SET birthdate_year = YEAR(birthdate);
    SET current_month = MONTH(CURDATE());
    SET current_year = YEAR(CURDATE());

    SET age_in_months = (current_year - birthdate_year) * 12 + (current_month - birthdate_month);

    SET age_text = CONCAT(FLOOR(age_in_months / 12), ' лет, ', MOD(age_in_months, 12), ' месяцев');

    RETURN age_text;
END //

DELIMITER ;

CREATE TEMPORARY TABLE ds AS  -- Создаем временную таблицу, куда выберутся все животные в нужном нам виде
	(SELECT Animal AS Type, Name, BirthDate, Commands, CalculateAgeInMonths(BirthDate) AS Age FROM Pets 
		LEFT JOIN Cats ON Cats.AnimalId = Pets.Id
	UNION
	SELECT Animal AS Type, Name, BirthDate, Commands, CalculateAgeInMonths(BirthDate) AS Age FROM Pets
		LEFT JOIN Dogs ON Dogs.AnimalId = Pets.Id
	UNION
	SELECT Animal AS Type, Name, BirthDate, Commands, CalculateAgeInMonths(BirthDate) AS Age FROM Pets
		LEFT JOIN Hamsters ON Hamsters.AnimalId = Pets.Id
	UNION
	SELECT Animal AS Type, Name, BirthDate, Commands, CalculateAgeInMonths(BirthDate) AS Age FROM PackAnimals
		LEFT JOIN Horses ON Horses.AnimalId = PackAnimals.Id
	UNION
	SELECT Animal AS Type, Name, BirthDate, Commands, CalculateAgeInMonths(BirthDate) AS Age FROM PackAnimals
		LEFT JOIN Donkeys ON Donkeys.AnimalId = PackAnimals.Id);

INSERT INTO Youngs SELECT * FROM ds  -- Заполняем таблицу из временной с заданным условием
	WHERE Age LIKE '1 лет%' OR Age LIKE '2 лет%';
    
/*
При помощи выборки объединяем все таблицы. Верблюды сюда не вошли, т.к. мы их удалили ранее. Колонка `FromTable` показывает, в какой таблице
изначально было животное, `Type` - к какому типу относится.
*/

SELECT Cats.Id, Name, Commands, Animal AS FromTable, Type FROM Cats  
	LEFT JOIN Pets ON Cats.AnimalId = Pets.Id
	LEFT JOIN Animals ON TypeId = Animals.Id
UNION
SELECT Dogs.Id, Name, Commands, Animal AS FromTable, Type FROM Dogs
	LEFT JOIN Pets ON Dogs.AnimalId = Pets.Id
	LEFT JOIN Animals ON TypeId = Animals.Id
UNION
SELECT Hamsters.Id, Name, Commands, Animal AS FromTable, Type FROM Hamsters
	LEFT JOIN Pets ON Hamsters.AnimalId = Pets.Id
	LEFT JOIN Animals ON TypeId = Animals.Id
UNION
SELECT Horses.Id, Name, Commands, Animal AS FromTable, Type FROM Horses
	LEFT JOIN PackAnimals ON Horses.AnimalId = PackAnimals.Id
	LEFT JOIN Animals ON TypeId = Animals.Id
UNION
SELECT Donkeys.Id, Name, Commands, Animal AS FromTable, Type FROM Donkeys
	LEFT JOIN PackAnimals ON Donkeys.AnimalId = PackAnimals.Id
	LEFT JOIN Animals ON TypeId = Animals.Id;
    
/*
База создна в MySQL Workbench.
*/