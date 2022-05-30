import React from 'react'
import './App.scss';
import TeamPage from "./pages/TeamPage";
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import MatchPage from "./pages/MatchPage";

function App() {
  return (

      <>
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/teams/:teamName" element={<TeamPage />} />
                    <Route path="/teams/:teamName/matches/:year" element={<MatchPage />} />
                </Routes>
            </BrowserRouter>
        </div>
      </>

  );
}

export default App;
