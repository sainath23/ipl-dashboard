import React from 'react'
import './App.scss';
import TeamPage from "./pages/TeamPage";
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import MatchPage from "./pages/MatchPage";
import HomePage from "./pages/HomePage";

function App() {
  return (

      <>
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/teams" element={<HomePage />} />
                    <Route path="/teams/:teamName" element={<TeamPage />} />
                    <Route path="/teams/:teamName/matches/:year" element={<MatchPage />} />
                </Routes>
            </BrowserRouter>
        </div>
      </>

  );
}

export default App;
