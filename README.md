# Application de gestion de compte bancaire

Cette application permet d'effectuer diverses opérations sur un compte bancaire, telles que le dépôt et le retrait d'argent, la consultation du solde actuel et l'affichage des transactions précédentes.

## Opérations disponibles

1. **Dépôt d'argent**
- **URL** : `/v2/account/{id}/deposit`
- **Méthode** : `PATCH`
- **Corps de la requête** :
    ```json
    {
        "amount": 5
    }
    ```
- **Réponse** :
    - HTTP 200 OK (en cas de succès) :
        ```json
        {
            "amount": 5,
            "message": "Deposit of amount '5' successful."
        }
        ```

2. **Retrait d'argent**
- **URL** : `/v2/account/{id}/withdrawal`
- **Méthode** : `PATCH`
- **Corps de la requête** :
    ```json
    {
        "amount": 3
    }
    ```
- **Réponse** :
    - HTTP 200 OK (en cas de succès) :
        ```json
        {
            "amount": 3,
            "message": "Withdrawal of amount '3' successful."
        }
        ```

3. **Consulter le solde actuel**
- **URL** : `/v2/account/{id}`
- **Méthode** : `GET`
- **Réponse** :
    ```json
    {
        "balance": 99
    }
    ```

4. **Consulter les transactions précédentes**
- **URL** : `/v2/account/{id}/transaction`
- **Méthode** : `GET`
- **Réponse** :
    ```json
    [
        {
            "amount": 5,
            "transactionDateTime": "2024-03-29T04:35:47.997767Z",
            "type": "DEPOSIT"
        },
        {
            "amount": 3,
            "transactionDateTime": "2024-03-29T04:35:52.153387Z",
            "type": "WITHDRAWAL"
        },
        {
            "amount": 3,
            "transactionDateTime": "2024-03-29T04:35:52.942067Z",
            "type": "WITHDRAWAL"
        }
    ]
    ```

## Gestion des erreurs
Tous les endpoints de cette application peuvent renvoyer les réponses HTTP suivantes :
- HTTP 400 BAD REQUEST en cas de requête mal formulée. Cela peut se produire si les données de la requête ne respectent pas le format attendu.
- HTTP 500 INTERNAL SERVER ERROR en cas d'erreur interne du serveur.
- HTTP 404 Not Found en cas de 'Account' inexistant.

## Instructions de démarrage

- TODO: ajouter les instructions Docker

## Licence
[Licence MIT](https://opensource.org/licenses/MIT)

*Auteur : [Tariq BOUKOUYEN](https://github.com/TariqBkn)*
