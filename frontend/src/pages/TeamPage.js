import React, { useEffect, useState } from 'react'
import { useParams } from "react-router-dom";
import MatchDetailCard from "../components/MatchDetailCard";
import MatchSmallCard from "../components/MatchSmallCard";

export default function TeamPage() {

    const [team, setTeam] = useState({data: {matches: []}});
    const { teamName } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/teams/${teamName}`);
                const payload = await response.json();
                console.log(payload);
                setTeam(payload.data)
            };
            fetchMatches();
        },
        []
    );

    if (!team || !team.teamName) {
        return <h1>Team not found!</h1>
    }

    return (
        <div className="TeamPage">
            <h1>{team.teamName}</h1>
            <MatchDetailCard teamName={team.teamName} match={team.matches[0]} />
            {team.matches.slice(1).map(match => <MatchSmallCard key={match.id} teamName={team.teamName} match={match} />)}
        </div>
    );
}