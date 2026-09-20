-- Active: 1789486337362@@127.0.0.1@5433@subtrack_db

DROP TABLE IF EXISTS paiements CASCADE;

DROP TABLE IF EXISTS abonnements CASCADE;

DROP TABLE IF EXISTS users CASCADE;

DROP TABLE IF EXISTS statuts CASCADE;

-- 1. Table Statuts
CREATE TABLE statuts ( status VARCHAR(30) PRIMARY KEY );

INSERT INTO
    statuts (status)
VALUES ('ACTIF'),
    ('INACTIF'),
    ('SUSPENDU'),
    ('PAYE'),
    ('EN_ATTENTE'),
    ('EN_RETARD'),
    ('RESILIE')
    ;

-- 2. Table Utilisateurs
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO
    users (
        id,
        nom,
        prenom,
        email,
        password
    )
VALUES (
        gen_random_uuid ()::text,
        'smail',
        'lakroune',
        'smail@smail.com',
        'smail'
    );

SELECT * FROM users;
-- 3. Table Abonnements
CREATE TABLE abonnements (
    id VARCHAR(50) PRIMARY KEY,
    idUser VARCHAR(50) NOT NULL,
    nomService VARCHAR(100) NOT NULL,
    montantMensuel NUMERIC(10, 2) NOT NULL,
    dateDebut DATE NOT NULL,
    dateFin DATE,
    statut VARCHAR(30) NOT NULL,
    typeAbonnement VARCHAR(30) NOT NULL CHECK (
        typeAbonnement IN (
            'AVEC_ENGAGEMENT',
            'SANS_ENGAGEMENT'
        )
    ),
    dureeEngagementMois INT CHECK (
        dureeEngagementMois > 0
        OR dureeEngagementMois IS NULL
    ),
    CONSTRAINT fk_abonnement_user FOREIGN KEY (idUser) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_abonnement_statut FOREIGN KEY (statut) REFERENCES statuts (status)
);

SELECT * FROM abonnements;

DELETE from abonnements;
 
CREATE TABLE paiements (
    idPaiement VARCHAR(50) PRIMARY KEY,
    idAbonnement VARCHAR(50) NOT NULL,
    dateEcheance DATE NOT NULL,
    datePaiement DATE,
    typePaiement VARCHAR(50) NOT NULL,
    statut VARCHAR(50) NOT NULL DEFAULT 'EN_ATTENTE',
    
    CONSTRAINT fk_paiement_abonnement 
        FOREIGN KEY (idAbonnement) 
        REFERENCES abonnements (id) 
        ON DELETE CASCADE,
        
    CONSTRAINT chk_paiement_statut 
        CHECK (statut IN ('EN_ATTENTE', 'PAYE', 'EN_RETARD', 'ANNULE'))
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_abonnements_user ON abonnements(idUser);
CREATE INDEX idx_abonnements_statut ON abonnements(statut);
CREATE INDEX idx_paiements_abonnement ON paiements(idAbonnement);