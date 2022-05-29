import React from 'react'

export default function MatchDetailCard({teamName, match}) {
    if (!match) return null;

    const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;

    return (
        <div className="MatchDetailCard">
            <h3>Latest Matches</h3>
            <h1>Vs {opponentTeam}</h1>
            <h2>{match.matchDate}</h2>
            <h3>at {match.venue}</h3>
            <h3>{match.matchWinner} won by {match.resultMargin} {match.result}</h3>
        </div>
    );
}