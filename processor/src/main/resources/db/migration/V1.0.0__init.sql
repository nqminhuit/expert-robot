CREATE TABLE nypost_sitemap (
       id UUID PRIMARY KEY,
       year INT NOT NULL,
       month INT NOT NULL,
       url VARCHAR(255) NOT NULL
);

-- INSERT INTO nypost_sitemap (id, year, month, url) values
--        (gen_random_uuid(), 2024, 11, 'www.abc.com/2024/11'),
--        (gen_random_uuid(), 2024, 10, 'www.abc.com/2024/10'),
--        (gen_random_uuid(), 2024, 09, 'www.abc.com/2024/09'),
--        (gen_random_uuid(), 2024, 08, 'www.abc.com/2024/08');
