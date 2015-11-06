'use strict';

function HomeController() {
    var vm = this;
    vm.mensagem = 'Teste';
}
function HomeRoute($stateProvider) {
    $stateProvider.state('home', {
        url: '/',
        templateUrl: 'views/home.html',
        controller: 'HomeController as home'
    });
}
angular.module('contatos')
        .config(HomeRoute)
        .controller('HomeController', HomeController);