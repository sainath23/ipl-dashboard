import React from 'react'
import {Link} from "react-router-dom";

export default function MatchSmallCard({teamName, match}) {
    if (!match) return null;

    const opponentTeam = match.team1 === teamName ? match.team2 : match.team1;
    const opponentTeamRoute = `/teams/${opponentTeam}`;

    return (
        <div className="MatchSmallCard">
            <h3>Vs <Link to={opponentTeamRoute}>{opponentTeam}</Link></h3>
            <p>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
        </div>
    );
}