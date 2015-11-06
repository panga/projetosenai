'use strict';

angular.module('contatos')
    .factory('Contato', 
        function(railsResourceFactory, API_URL) {
        var Contato = railsResourceFactory({
            url: API_URL + 'contatos'
        });

        return Contato;
    });