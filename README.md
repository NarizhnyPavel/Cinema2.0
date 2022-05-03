# Cinema2.0
Курсовой проект. Клиент-серверное приложение для ведения каталога фильмов.

Для запуска необходимо:
1. поднять локально базу Postgres 11 на порту 5432 для подключения по jdbc:postgresql://localhost:5432/DataForKino
2. раскатить на ней [backup](https://github.com/NarizhnyPavel/Cinema2.0/blob/master/backup.backup)
4. запустить [Server](https://github.com/NarizhnyPavel/Cinema2.0/blob/master/Server/src/Kinopoisk/server/ServerLauncher.java) на Tomcat сервер
5. запустить [Client](https://github.com/NarizhnyPavel/Cinema2.0/tree/master/Client) на Tomcat сервере для отображения клиентского тонкого клиента
