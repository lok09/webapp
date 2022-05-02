CREATE TABLE lecture(
    lecture_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    lecture_title VARCHAR(50) NOT NULL,
    PRIMARY KEY (lecture_id)
);

CREATE TABLE user_info(
    username VARCHAR(20) NOT NULL,
    password VARCHAR(25) NOT NULL,
    user_role VARCHAR(20) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    phone_number INTEGER NOT NULL,
    address VARCHAR(250) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE material(
    material_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    material_name VARCHAR(50) NOT NULL,
    owner_username VARCHAR(50) NOT NULL,
    mime_content_type VARCHAR(255) NOT NULL,
    contents BLOB NOT NULL,
    upload_date TIMESTAMP,
    lecture_id INTEGER NOT NULL,
    PRIMARY KEY (material_id),
    FOREIGN KEY (lecture_id) REFERENCES lecture(lecture_id)
);

CREATE TABLE lecturecomment(
    comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    content VARCHAR(250) NOT NULL,
    create_date TIMESTAMP,
    lecture_id INTEGER NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (lecture_id) REFERENCES lecture(lecture_id),
    FOREIGN KEY (user_id) REFERENCES user_info(username)
);

CREATE TABLE poll(
    poll_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    question VARCHAR(200) NOT NULL,
    option_a VARCHAR(50) NOT NULL,
    option_b VARCHAR(50) NOT NULL,
    option_c VARCHAR(50) NOT NULL,
    option_d VARCHAR(50) NOT NULL,
    answer VARCHAR(1) NOT NULL,
    create_date TIMESTAMP,
    PRIMARY KEY (poll_id)
);

CREATE TABLE pollcomment(
    comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    content VARCHAR(250) NOT NULL,
    create_date TIMESTAMP,
    poll_id INTEGER NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (poll_id) REFERENCES poll(poll_id),
    FOREIGN KEY (user_id) REFERENCES user_info(username)
);

CREATE TABLE pollresult(
    poll_result_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    user_option VARCHAR(1) NOT NULL,
    submit_date TIMESTAMP,
    poll_id INTEGER NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (poll_result_id),
    FOREIGN KEY (poll_id) REFERENCES poll(poll_id),
    FOREIGN KEY (user_id) REFERENCES user_info(username)
);



INSERT INTO USER_INFO(username, address, full_name, password, phone_number, user_role)
VALUES ('studentTest', '3 sunsain street, Hon Man Tin', 'Victor Wong', '{noop}studentTestpw', 62542156, 'ROLE_STUDENT');

INSERT INTO USER_INFO(username, address, full_name, password, phone_number, user_role)
VALUES ('LeeSzeChit', '3 sunsain street, Hon Man Tin', 'Sergio Lee', '{noop}LeeSzeChitpw', 66008802, 'ROLE_STUDENT');

INSERT INTO USER_INFO(username, address, full_name, password, phone_number, user_role)
VALUES ('lectureTest', 'HKMU', 'Keith Lee', '{noop}lectureTestpw', 58002150, 'ROLE_LECTURER');

INSERT INTO USER_INFO(username, address, full_name, password, phone_number, user_role)
VALUES ('Jeff', 'HKMU', 'Jeff Au', '{noop}Jeffpw', 52143168, 'ROLE_LECTURER');

