-- V2_insert_data.sql
INSERT INTO "house-reservation-system".house (name, description, type, capacity, price, location_type) VALUES
-- 5 DOMEK w lokalizacji FIRST
('Chatka Zygi', 'Przytulny drewniany domek otoczony sosnami, idealny na rodzinne wakacje', 'DOMEK', 4, 199.99, 'FIRST'),
('Korki nad Jeziorem', 'Domek z własnym pomostem, perfekcyjny dla wędkarzy i miłośników natury', 'DOMEK', 6, 249.99, 'FIRST'),
('Leśna Przystań Nowaków', 'Osłonięty w lesie domek z prywatnymi ścieżkami do spacerów', 'DOMEK', 4, 179.99, 'FIRST'),
('Słoneczne Wzgórze', 'Jasny domek z dużym ogrodem i miejscem na grill', 'DOMEK', 5, 219.99, 'FIRST'),
('Łąkowe Zacisze', 'Widok na kwieciste łąki, świetny dla dzieci i zwierząt', 'DOMEK', 4, 189.99, 'FIRST'),
-- 1 DOM w lokalizacji FIRST
('Willla Korki', 'Luksusowy dom z basenem, sauną i inteligentnym systemem sterowania', 'DOM', 8, 599.99, 'FIRST'),
-- 7 DOMEK w lokalizacji SECOND
('Górski Azyl Zygmunta', 'Wysokogórski domek z widokiem na szczyty', 'DOMEK', 3, 149.99, 'SECOND'),
('Alpejska Chatka', 'Autentyczny drewniany domek w stylu tyrolskim', 'DOMEK', 4, 169.99, 'SECOND'),
('Nowaków Zakątek nad Rzeką', 'Kameralny domek przy szumiącym potoku', 'DOMEK', 2, 129.99, 'SECOND'),
('Szklana Perła', 'Nowoczesny loft z panoramicznymi oknami', 'DOMEK', 4, 199.99, 'SECOND'),
('Sosnowy Gaj', 'Otoczony starodrzewem, idealny na relaks', 'DOMEK', 5, 209.99, 'SECOND'),
('Gwiazdny Patrol', 'Dach z przezroczystego szkła do obserwacji nieba', 'DOMEK', 2, 159.99, 'SECOND'),
('Zachodnie Słońce', 'Domek z tarasem widokowym na zachody słońca', 'DOMEK', 6, 229.99, 'SECOND');

INSERT INTO "house-reservation-system".house_amenities (house_id, amenity) VALUES
-- Domek 1 (Chatka Zygi)
(1, 'WiFi'), (1, 'Kominek'), (1, 'Taras'), (1, 'Kuchnia'),
-- Domek 2 (Korki nad Jeziorem)
(2, 'Pomost'), (2, 'Grill'), (2, 'Klimatyzacja'), (2, 'Parking'),
-- Domek 3 (Leśna Przystań Nowaków)
(3, 'Ścieżki rowerowe'), (3, 'Jacuzzi'), (3, 'Piec kaflowy'), (3, 'Akceptacja zwierząt'),
-- Domek 4 (Słoneczne Wzgórze)
(4, 'Ogród'), (4, 'Panele słoneczne'), (4, 'Pralka'), (4, 'TV SAT'),
-- Domek 5 (Łąkowe Zacisze)
(5, 'Miejsce na ognisko'), (5, 'Rowery'), (5, 'Plac zabaw'),
-- Dom 6 (Willla Korki)
(6, 'Basen'), (6, 'Sauna'), (6, 'Kino domowe'), (6, 'Inteligentny dom'), (6, 'Siłownia'),
-- Domek 7 (Górski Azyl Zygmunta)
(7, 'Widok na góry'), (7, 'Ogrzewanie podłogowe'), (7, 'Mini kuchnia'),
-- Domek 8 (Alpejska Chatka)
(8, 'Kominek'), (8, 'Narty'), (8, 'Gry planszowe'),
-- Domek 9 (Nowaków Zakątek)
(9, 'Sprzęt wędkarski'), (9, 'Kajaki'), (9, 'Namiot'),
-- Domek 10 (Szklana Perła)
(10, 'Netflix'), (10, 'Balkon'), (10, 'Ekspres do kawy'),
-- Domek 11 (Sosnowy Gaj)
(11, 'Hamaki'), (11, 'Miejsce na grill'), (11, 'Leżaki'),
-- Domek 12 (Gwiazdny Patrol)
(12, 'Teleskop'), (12, 'Podgrzewane koce'), (12, 'Głośnik Bluetooth'),
-- Domek 13 (Zachodnie Słońce)
(13, 'Jacuzzi na zewnątrz'), (13, 'Stół biesiadny'), (13, 'Rowery górskie');
