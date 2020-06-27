package com.cursorestapi.apirest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.model.Disciplina;

@Service
public class RankService {

	public List<Disciplina> quickSort(List<Disciplina> dados, int primeiroIndice, int ultimoIndice) {

		if (primeiroIndice < ultimoIndice) {
			int meio = particiona(dados, primeiroIndice, ultimoIndice);
			quickSort(dados, primeiroIndice, meio - 1);
			quickSort(dados, meio + 1, ultimoIndice);
		}

		return dados;

	}

	public int particiona(List<Disciplina> dados, int primeiroIndice, int ultimoIndice) {

        double pivo = dados.get(ultimoIndice).getNota();
        int i = primeiroIndice - 1;

        for(int j = primeiroIndice; j < ultimoIndice; j++) {
        	if (dados.get(j).getNota() > pivo) {
        		i = i + 1;
        		dados.set(i, dados.get(j));
        	}
        }
        dados.set(ultimoIndice, dados.get(i + 1));
        return i + 1;
}
}
