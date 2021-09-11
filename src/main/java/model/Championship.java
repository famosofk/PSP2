package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Championship {

    private final int totalTeams;
    private final List<Team> teamList;

    public int getTeamsNumber() {
        return totalTeams;
    }

    public Championship(int teamsNumber) throws IllegalArgumentException {
        if (teamsNumber < 2) {
            throw new IllegalArgumentException("Invalid number of teams");
        }
        this.totalTeams = teamsNumber;
        teamList = new ArrayList<>(teamsNumber);
        for (int i = 0; i < teamsNumber; i++) {
            teamList.add(new Team(i + 1));
        }
        System.out.println("value");
    }

    public Team getTeam(int teamNumber) throws IllegalAccessException {
        if (teamNumber > totalTeams || teamNumber <= 0) {
            throw new IllegalAccessException("There's no team with this number");
        }
        return teamList.get(teamNumber);
    }

    public int getTotalMatches() {
        return totalTeams * (totalTeams - 1) / 2;
    }

    public void processMatch(String line) {
        String[] data = line.trim().split(" ");
        int winner, loser, winnerPoints, loserPoints;
        int firstTeamScore = Integer.parseInt(data[1]);
        int secondTeamScore = Integer.parseInt(data[3]);
        int firstTeamNumber = Integer.parseInt(data[0]);
        int secondTeamNumber = Integer.parseInt(data[2]);

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

    private void updateWinner(int teamNumber, int scoredPoints, int sufferedPoints) {
        updateTeam(teamNumber, scoredPoints, sufferedPoints, MatchResult.WIN);
    }

    private void updateLoser(int teamNumber, int scoredPoints, int sufferedPoints) {
        updateTeam(teamNumber, scoredPoints, sufferedPoints, MatchResult.LOSE);
    }

    private void updateTeam(int teamNumber, int scoredPoints, int sufferedPoints, MatchResult result) {
        Team team = teamList.get(teamNumber - 1);
        team.addMadePoints(scoredPoints);
        team.addSufferedPoints(sufferedPoints);
        team.addScore(result.value);
        teamList.set(teamNumber - 1, team);
    }

    public String getResult() {
        StringBuilder result = new StringBuilder();
        for (Team t : getFinalScoreBoard()) {
            result.append(" ").append(t.getNumber());
        }
        return result.toString().trim();
    }


    public List<Team> getFinalScoreBoard() {
        return teamList.stream().sorted((o1, o2) -> {
            int result = 0;
            for (int i = 0; i < 4; i++) {
                if (result != 0) return result;
                switch (i) {
                    //Estamos usando o comparable do Integer e Double pra não ter que escrever o nosso.
                    //Como queremos a lista ao contrário, vamos multiplicar o resultado por -1.
                    //Mais eficiente que dar um Collections.reverse()
                    case 0:
                        result = Integer.compare(o1.getScore(), o2.getScore()) * -1;
                        break;
                    case 1:
                        result = Double.compare(o1.getAverageScore(), o2.getAverageScore()) * -1;
                        break;
                    case 2:
                        result = Integer.compare(o1.getMadePoints(), o2.getMadePoints()) * -1;
                        break;
                    case 3:
                        result = Integer.compare(o1.getNumber(), o2.getNumber()) * -1;
                }
            }
            return 0;
        }).collect(Collectors.toList());
    }


}
