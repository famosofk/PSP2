package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Championship {

    private final int totalTeams;
    private final List<Team> teamList;

    public int getTeamsNumber() {
        return totalTeams;
    }


    /**
     * Complexidade: 3.
     * Apresenta um if para não criar campeonato com 1 único time. +1
     * Apresenta um for para a criação dos times na entidade campeonato.
     **/
    public Championship(int teamsNumber) throws IllegalArgumentException {
        if (teamsNumber < 2) {
            throw new IllegalArgumentException("Invalid number of teams");
        }
        this.totalTeams = teamsNumber;
        teamList = new ArrayList<>(teamsNumber);
        for (int i = 0; i < teamsNumber; i++) {
            teamList.add(new Team(i + 1));
        }
    }


    /**
     * Complexidade: 3.
     * Como esse é um método get, para prevenir acesso inválido a nossa lista, temos um if que verifica o valor passado.
     * por esse if conter um condicional ou temos +2.
     **/
    public Team getTeam(int teamNumber) throws IllegalAccessException {
        if (teamNumber > totalTeams || teamNumber <= 0) {
            throw new IllegalAccessException("There's no team with this number");
        }
        return teamList.get(teamNumber - 1);
    }

    /**
     * Complexidade: 1. Código sequencial.
     **/
    public int getTotalMatches() {
        return totalTeams * (totalTeams - 1) / 2;
    }


    /**
     * Complexidade: 2. O código apresenta um condicional para definir o time vencedor.
     **/
    public void processMatch(int firstTeamScore, int secondTeamScore, int firstTeamNumber, int secondTeamNumber) {
        int winner, loser, winnerPoints, loserPoints;

        if (firstTeamScore > secondTeamScore) {
            winner = firstTeamNumber;
            winnerPoints = firstTeamScore;
            loser = secondTeamNumber;
            loserPoints = secondTeamScore;
        } else {
            winner = secondTeamNumber;
            winnerPoints = secondTeamScore;
            loser = firstTeamNumber;
            loserPoints = firstTeamScore;
        }

        updateWinner(winner, winnerPoints, loserPoints);
        updateLoser(loser, loserPoints, winnerPoints);
    }

    /**
     * Complexidade: 1. Essa função é desnecessária, porém já vi muita gente burra, então prefiro colocar.
     **/
    private void updateWinner(int teamNumber, int scoredPoints, int sufferedPoints) {
        updateTeam(teamNumber, scoredPoints, sufferedPoints, MatchResult.WIN);
    }

    /**
     * Complexidade: 1. Essa função é desnecessária, porém já vi muita gente burra, então prefiro colocar.
     **/
    private void updateLoser(int teamNumber, int scoredPoints, int sufferedPoints) {
        updateTeam(teamNumber, scoredPoints, sufferedPoints, MatchResult.LOSE);
    }

    /**
     * Complexidade: 1. Código sequencial
     **/
    private void updateTeam(int teamNumber, int scoredPoints, int sufferedPoints, MatchResult result) {
        Team team = teamList.get(teamNumber - 1);
        team.addMadePoints(scoredPoints);
        team.addSufferedPoints(sufferedPoints);
        team.addScore(result.value);
        teamList.set(teamNumber - 1, team);
    }


    /**
     * Complexidade: 2. Temos um for que adiciona um ao valor. Apenas formato o resultado.
     **/
    public String getResult() {
        StringBuilder result = new StringBuilder();
        for (Team t : getFinalScoreBoard()) {
            result.append(" ").append(t.getNumber());
        }
        return result.toString().trim();
    }

    /**
     * Complexidade: 8. (1 inicial +1 +1 +1 +4)
     * Aqui temos uma função de ordenação implementada em função dos critérios do campeonato.
     * O for que percorre os critérios de desempate. +1 ponto.
     * O if verifica se já está ordenado. +1.
     * Como temos 2 returns no algoritmo. +1.
     * Temos 4 critérios de ordenação. +4.
     * O switch foi utilizado para em caso de empate, definirmos qual critério estamos utilizando.
     **/
    public List<Team> getFinalScoreBoard() {
        List<Team> list = teamList.stream().sorted((o1, o2) -> {
            int result = 0;
            for (int i = 0; i < 4; i++) {
                if (result != 0) return result;
                switch (i) {
                    case 0:
                        result = Integer.compare(o1.getScore(), o2.getScore());
                        break;
                    case 1:
                        result = Double.compare(o1.getAverageScore(), o2.getAverageScore());
                        break;
                    case 2:
                        result = Integer.compare(o1.getMadePoints(), o2.getMadePoints());
                        break;
                    case 3:
                        result = Integer.compare(o1.getNumber(), o2.getNumber());
                }
            }
            return 0;
        }).collect(Collectors.toList());
        Collections.reverse(list);
        return list;
    }
}