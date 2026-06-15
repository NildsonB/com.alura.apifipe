package com.alura.apifipe.principal;

import com.alura.apifipe.model.*;
import com.alura.apifipe.service.ConsulmoApi;
import com.alura.apifipe.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    Scanner entrada = new Scanner(System.in);
    ConsulmoApi consulmoApi = new ConsulmoApi();
    ConverteDados conversor = new ConverteDados();

    private final String URIBASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        System.out.println("---OPÇÕES---");
        System.out.println("Carro");
        System.out.println("Moto");
        System.out.println("Caminhão");
        System.out.print("Digite uma das opções para consultar valores: ");
        String tipoVeiculo = entrada.nextLine();
        String endereco;
        //Seleção do tipo de veículo
        if (tipoVeiculo.toLowerCase().contains("car")) {
            endereco = URIBASE + "carros/marcas";
        } else if (tipoVeiculo.toLowerCase().contains("mot")) {
            endereco = URIBASE + "motos/marcas";
        } else {
            endereco = URIBASE + "caminhoes/marcas";
        }
        //Consumo da API e conversão para classe
        String json = consulmoApi.obterDados(endereco);
        List<DTODados> dadosMarcas = conversor.converteLista(json, DTODados.class);
        List<Dados> marcas = dadosMarcas.stream()
                .map(d -> new Dados(d))
                .sorted(Comparator.comparing(Dados::getNome))
                .collect(Collectors.toList());
        marcas.forEach(System.out::println);
        //Seleção da marca do veículo
        System.out.print("Informe o código da marca para consulta: ");
        String codigoMarca = entrada.nextLine();
        endereco = endereco + "/" + codigoMarca + "/modelos";
        json = consulmoApi.obterDados(endereco);
        DTOModelos dadosModelos = conversor.converteDados(json, DTOModelos.class);
        List<Dados> modelos = dadosModelos.modelos().stream()
                .map(m -> new Dados(m))
                .sorted(Comparator.comparing(Dados::getNome))
                .collect(Collectors.toList());
        modelos.forEach(System.out::println);
        //Filtro para modelo
        System.out.print("Digite um trecho do nome para consulta: ");
        String trecho = entrada.nextLine();
        modelos.stream()
                .filter(m -> m.getNome().toLowerCase().contains(trecho.toLowerCase()))
                .sorted(Comparator.comparing(Dados::getNome))
                .forEach(System.out::println);
        //Seleção do modelo desejado
        System.out.print("Digite o código do modelo para consulta de valores: ");
        String codigoModelo = entrada.nextLine();
        endereco = endereco + "/" + codigoModelo + "/anos";
        System.out.println(endereco);
        json = consulmoApi.obterDados(endereco);
        List<DTODados> dadosPorAno = conversor.converteLista(json, DTODados.class);
        //Apresentação dos dados de cada ano registrado
        System.out.println("Todos os veículos com os valores por ano:");
        for (int i = 0; i < dadosPorAno.size(); i++) {
            json = consulmoApi.obterDados(endereco + "/" + dadosPorAno.get(i).codigo());
            DTOVeiculos dadosVeiculo = conversor.converteDados(json, DTOVeiculos.class);
            Veiculo veiculo = new Veiculo(dadosVeiculo);
            System.out.println(veiculo);
        }
    }
}
