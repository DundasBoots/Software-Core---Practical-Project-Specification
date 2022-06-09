'use strict';

// Create
const firstName = document.getElementById("FirstName");
const surname = document.getElementById("Surname");
const age = document.getElementById("Age");
const nationality = document.getElementById("Nationality");
const raceWins = document.getElementById("Wins");
const bestChampPos = document.getElementById("ChampPos");
const teamId = document.getElementById("TeamId");

// Update
let ID = document.getElementById("DriverID");
const ufirstName = document.getElementById("uFirstName");
const usurname = document.getElementById("uSurname");
const uage = document.getElementById("uAge");
const unationality = document.getElementById("uNationality");
const uraceWins = document.getElementById("uWins");
const ubestChampPos = document.getElementById("uChampPos");

// Create Function
async function createDriver() {
    let addDriver = await fetch(`http://localhost:8080/driver/create`, {
        method: 'post',
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            first_name: `${firstName.value}`,
            surname: `${surname.value}`,
            age: `${age.value}`,
            nationality: `${nationality.value}`,
            race_wins: `${raceWins.value}`,
            best_championship_position: `${bestChampPos.value}`,
            // team_id: `${teamId.value}`
        }),
    })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
}

// Read Function
async function readAllDriver() {
    let response = await fetch(`http://localhost:8080/driver/readAll`);
    if (response.status !== 201) console.error(`status: ${response.status}`);
    let data = await response.json();
    let tableData = "";
    data.map((values) => {
        tableData += `<tr>
        <td>${values.id}</td>
        <td>${values.first_name}</td>
        <td>${values.surname}</td>
        <td>${values.age}</td>
        <td>${values.nationality}</td>
        <td>${values.race_wins}</td>
        <td>${values.best_championship_position}</td>
      </tr>`
    });
    document.getElementById("table_body").innerHTML = tableData;
}

// async function readById(id){
//     let response1 = await fetch (`http://localhost:8080/driver/readById/${id}`);
//     if (response1.status !=201) console.error(`status: ${response1.status}`);
//     let data1 = await response1.json();
//     showUpdate(data1);
// }

function showUpdate(data1) {
    for (let i in data1) {
      let child = document.createElement(`li`);
      child.appendChild(document.createTextNode(`${data1[i]}`))
      updateList.appendChild(child)
    }
}


// Update Function 
async function updateDriver() {
    let id = ID.value
    let changeDriver = await fetch(`http://localhost:8080/driver/update/${ID}`, {
        method: 'put',
        headers: { "Content-type": "application/json" },
        body: JSON.stringify({
            first_name: `${ufirstName.value}`,
            surname: `${usurname.value}`,
            age: `${uage.value}`,
            nationality: `${unationality.value}`,
            race_wins: `${uraceWins.value}`,
            best_championship_position: `${ubestChampPos.value}`
        }),
    })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
}

// Delete Function 
async function deleteDriver(id) {
    let msg = `Please confirm that you wish to delete your selected entries.`
    if (confirm(msg)) {
        let response = await fetch(`http://localhost:8080/driver/delete/{id}`,
            { method: 'delete' });
        if (response.status !== 204) console.error(`status: ${response.status}`);
        await readAllDriver();
    }
}


function createAndRead() {
    let a = createDriver();
    let b = readAllDriver();
    return a && b;
}

function updateAndRead() {
    let c = updateDriver();
    let d = readAllDriver();
    return c && d;
}
