Locally application runs by lunching main class /src/main/java/building.registry.api/ApiApplication.class

/swagger-ui.html - documentation

Property branch:
    /v1/property POST method
                    accepts json format:
                    {
                      "city": "string",
                      "number": int,
                      "owner": "string",
                      "size": double,
                      "street": "string",
                      "type": "string",
                      "value": double
                    }
                type has to be known and declared in /v1/property/tax for json to be accepted.

    /v1/property/ GET method. Returns property list of owner. Requires parameter "owner" as String to be submited.
    /v1/property/{id} GET method. Returns property by id.
    /v1/property/{id} PUT method. Updates property by id. Property must be submitted the same format as in /v1/property POST
    /v1/property/{id} DELETE method. Deletes property by id.

    Tax branch: tax name is by property type.
    /v1/property/tax POST method. Accepts json format:
                            {
                              "type": "string",
                              "value": double
                            }
    /v1/property/tax/{type} GET method. Returns tax value by property type (String)
    /v1/property/tax/{type} PUT method. Updates tax value by property type (String). Requests value.
    /v1/property/tax/{type} DELETE method. DELETES tax.
    /v1/property/taxes GET method. Return all taxes added to database.

    /v1/property/taxes/annual/{owner} GET method. Calculates annual all property taxes by owner




