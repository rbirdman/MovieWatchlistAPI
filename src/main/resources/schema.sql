DROP TABLE IF EXISTS Media;
CREATE TABLE Media (
    id varchar(16),
    -- TODO: ratings
    title varchar(255),
    originalTitle varchar(255),
    fullTitle varchar(255),
    type varchar(255),
    year varchar(255),
    image varchar(255),
    releaseDate varchar(255),
    runtimeMins varchar(255),
    runtimeStr varchar(255),
    plot varchar(1024),
    -- TODO: actorList
    genres varchar(255),
    PRIMARY KEY (id));
