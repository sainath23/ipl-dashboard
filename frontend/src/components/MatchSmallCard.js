import React from 'react'

export default function MatchSmallCard({teamName, match}) {
    if (!match) return null;

    const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;

    return (
        <div className="MatchSmallCard">
            <h3>Vs {opponentTeam}</h3>
            <p>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
        </div>
    );
}