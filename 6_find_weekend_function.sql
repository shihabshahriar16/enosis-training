CREATE TABLE day_enum(
    id INT PRIMARY KEY
);
INSERT INTO day_enum VALUES(1);
INSERT INTO day_enum VALUES(2);
INSERT INTO day_enum VALUES(3);
INSERT INTO day_enum VALUES(4);
INSERT INTO day_enum VALUES(5);
INSERT INTO day_enum VALUES(6);
INSERT INTO day_enum VALUES(7);

SELECT day_enum.* FROM day_enum 
WHERE id NOT IN (SELECT DISTINCT(day)+0 from schedule
WHERE hall_id=2) LIMIT 1;


-- Function
DELIMITER $$   
CREATE FUNCTION find_weekend(query_hall INT) RETURNS INT   
DETERMINISTIC 
BEGIN   
    DECLARE weekend_id INT;  
    SELECT id INTO weekend_id FROM day_enum WHERE id = (SELECT day_enum.* FROM day_enum 
    WHERE id NOT IN (SELECT DISTINCT(day)+0 from schedule
    WHERE hall_id=query_hall) LIMIT 1); 
    RETURN weekend_id;  
END $$  

SELECT DISTINCT(hall_id),find_weekend(hall_id) FROM
schedule;