'use strict';

function ContatoController($scope, Contato) {
    $scope.limpar = function () {
        $scope.contato = {};
    }

    $scope.listar = function () {
        Contato.query().then(function (data) {
            $scope.lista = data;
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };

    $scope.gravar = function () {
        if ($scope.contato.dataNascimento) {
            $scope.contato.dataNascimento 
                    = new Date($scope.contato.dataNascimento)
                    .toISOString();
        }
        
        if ($scope.contato.id) {
            $scope.contato.update().then(function () {
                $scope.limpar();
                $scope.listar();
            }, function (error) {
                console.log('error', error);
                alert(error.data);
            });
        } else {
            new Contato($scope.contato).create()
                    .then(function () {
                        $scope.limpar();
                        $scope.listar();
                    }, function (error) {
                        console.log('error', error);
                        alert(error.data);
                    });
        }
    };

    $scope.editar = function (contato) {
        $scope.contato = angular.copy(contato);
    };

    $scope.deletar = function (contato) {
        contato.remove().then(function () {
            $scope.listar();
        }, function (error) {
            console.log('error', error);
            alert(error.data);
        });
    };
    
    function init() {
        $scope.limpar();
        $scope.listar();
    }
    
    init();
}
function ContatoRoute($stateProvider) {
    $stateProvider.state('contato', {
        url: '/contato',
        templateUrl: 'views/contato.html',
        controller: 'ContatoController'
    });
}
angular.module('contatos')
        .config(ContatoRoute)
        .controller('ContatoController', ContatoController);