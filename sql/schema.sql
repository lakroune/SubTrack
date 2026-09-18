-- Active: 1789486337362@@127.0.0.1@5433@subtrack_db
DROP TABLE IF EXISTS paiements CASCADE;
DROP TABLE IF EXISTS abonnements CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TYPE IF EXISTS statut_enum CASCADE;



CREATE TYPE statut_enum AS ENUM (
    'ACTIF',
    'INACTIF',
    'SUSPENDU',
    'PAYE',
    'EN_ATTENTE',
    'EN_RETARD'
);

CREATE TABLE users (
    id_user VARCHAR(50) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE abonnements (
    id VARCHAR(50) PRIMARY KEY,
    id_user VARCHAR(50) NOT NULL,
    nomservice VARCHAR(100) NOT NULL,
    montantMensuel NUMERIC(10, 2) NOT NULL,
    dateDebut DATE NOT NULL,
    dateFin DATE,
    statut statut_enum NOT NULL DEFAULT 'ACTIF',
    type_abonnement VARCHAR(30) NOT NULL CHECK (type_abonnement IN ('AVEC_ENGAGEMENT', 'SANS_ENGAGEMENT')),
    duree_engagement_mois INT CHECK (duree_engagement_mois > 0 OR duree_engagement_mois IS NULL),
    CONSTRAINT fk_abonnement_user 
        FOREIGN KEY (id_user) 
        REFERENCES users(id_user) 
        ON DELETE CASCADE
);

CREATE TABLE paiements (
    id_paiement VARCHAR(50) PRIMARY KEY,
    id_abonnement VARCHAR(50) NOT NULL,
    date_echeance DATE NOT NULL,
    date_paiement DATE,
    type_paiement VARCHAR(50) NOT NULL,
    statut statut_enum NOT NULL DEFAULT 'EN_ATTENTE',
    CONSTRAINT fk_paiement_abonnement 
        FOREIGN KEY (id_abonnement) 
        REFERENCES abonnements(id) 
        ON DELETE CASCADE
);

CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_abonnements_user ON abonnements(id_user);
CREATE INDEX idx_abonnements_statut ON abonnements(statut);
CREATE INDEX idx_paiements_abonnement ON paiements(id_abonnement);

