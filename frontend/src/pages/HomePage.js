import React, { useEffect, useState } from 'react'
import './HomePage.scss'
import TeamTile from "../components/TeamTile";

export default function HomePage() {

    const [teams, setTeams] = useState({data: []});

    useEffect(
        () => {
            const fetchAllTeams = async () => {
                const response = await fetch(`http://localhost:8080/teams`);
                const payload = await response.json();
                //console.log(payload);
                setTeams(payload.data)
                //console.log(teams)
            };
            fetchAllTeams();
        },
        []
    );

    return (
        <div className="HomePage">
            <div className="header-section">
                <h1 className="app-name">IPL Dashboard</h1>
            </div>

            <div className="team-grid">
                {
                    teams && teams.length && teams.map(team => <TeamTile key={team.id} teamName={team.teamName} />)
                }
            </div>

        </div>
    );
}