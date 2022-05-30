import React, { useEffect, useState } from 'react'
import { useParams } from "react-router-dom";
import MatchDetailCard from "../components/MatchDetailCard";

export default function MatchPage() {

    const [matches, setMatches] = useState({data: []});
    const { teamName, year } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/teams/${teamName}/matches?year=${year}`);
                const payload = await response.json();
                console.log(payload);
                setMatches(payload.data)
                console.log(matches)
            };
            fetchMatches();
        },
        []
    );

    if (!matches || matches.length === 0) {
        return <h1>Matches not found!</h1>
    }

    return (
        <div className="MatchPage">
            <h1>Match Page</h1>
            {
                matches && matches.map && matches.map(match => <MatchDetailCard key={match.id} teamName={teamName} match={match} />)
            }
        </div>
    );
}