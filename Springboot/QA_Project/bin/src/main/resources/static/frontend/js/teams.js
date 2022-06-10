'use strict';
    const teamName = document.getElementById("TeamName");
    const engineManufacturer = document.getElementById("EngineManufacturer");
    const teamPrincipal = document.getElementById("TeamPrincipal");
    const bestChampionship_position = document.getElementById("BestChampionshipFinish");
    const constructorsChampionships = document.getElementById("ConstructorsChampionshipWins");


async function createTeam() {
    let addDriver = await fetch(`http://localhost:8080/team/create`, {
        method: 'post',
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            team_name: `${teamName.value}`,
            engine_manufacturer: `${engineManufacturer.value}`,
            constructors_championships: `${constructorsChampionships.value}`,
            team_principal: `${teamPrincipal.value}`,
            best_championship_position: `${bestChampionship_position.value}`
        }),
    })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
}

async function readAllTeam() {
    let response = await fetch(`http://localhost:8080/team/readAll`);
    if (response.status !== 201) console.error(`status: ${response.status}`);
    let data = await response.json();
    let tableData = "";
    data.map((values) => {
        tableData += `<tr>
        <td>${values.id}</td>
        <td>${values.team_name}</td>
        <td>${values.engine_manufacturer}</td>
        <td>${values.team_principal}</td>
        <td>${values.constructors_championships}</td>
        <td>${values.best_championship_position}</td>
        <td>${values.drivers}</td>
      </tr>`
    });
    document.getElementById("table_body").innerHTML = tableData;
}

async function updateTeam() {
    let changeTeam = await fetch(`http://localhost:8080/team/update/{id}`, {
        method: 'put',
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            team_name: `${teamName.value}`,
            engine_manufacturer: `${engineManufacturer.value}`,
            constructors_championships: `${constructorsChampionships.value}`,
            team_principal: `${teamPrincipal.value}`,
            best_championship_position: `${bestChampionship_position.value}`
        }),
    })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
}

async function deleteTeam(id) {
    let msg = `Please confirm that you wish to delete your selected entries.`
    if (confirm(msg)) {
        let response = await fetch(`http://localhost:8080/team/delete/{id}`,
            { method: 'delete' });
        if (response.status !== 204) console.error(`status: ${response.status}`);
        await readAllTeam();
    }
}

function createAndReadTeam(){
    let a = createTeam();
    let b = readAllTeam();
    return a && b;
}

