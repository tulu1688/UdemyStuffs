DROP FUNCTION IF EXISTS FC_DISTANCE_CALCULATION;

DELIMITER //
CREATE FUNCTION FC_DISTANCE_CALCULATION(
	latitude1 DOUBLE,
    longitude1 DOUBLE,
    latitude2 DOUBLE,
    longitude2 DOUBLE
) RETURNS DOUBLE
BEGIN
	RETURN acos(
		SIN(RADIANS(latitude1)) * SIN(RADIANS(latitude2)) +
		COS(RADIANS(latitude1)) * COS (RADIANS(latitude2)) * COS ( RADIANS(longitude2 - longitude1))
        )
    * 6371300;
END;
//

DELIMITER ;

select FC_DISTANCE_CALCULATION(10, 10, 10, 10.1);
