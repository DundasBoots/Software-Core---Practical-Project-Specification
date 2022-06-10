'use strict';

// Create
const firstName = document.getElementById("FirstName");
const surname = document.getElementById("Surname");
const age = document.getElementById("Age");
const nationality = document.getElementById("Nationality");
const raceWins = document.getElementById("RaceWin");
const bestChampPos = document.getElementById("BestChamp");
const teamId = document.getElementById("TeamId");

// Update
const ID = document.getElementById("DriverID");
const ufirstName = document.getElementById("uFirstName");
const usurname = document.getElementById("uSurname");
const uage = document.getElementById("uAge");
const unationality = document.getElementById("uNationality");
const uraceWins = document.getElementById("uRaceWin");
const ubestChampPos = document.getElementById("uBestChamp");

//Buttons
const createButt = document.getElementById("create");
const read = document.getElementById("read");


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
            team: {
                id:`${ID.value}`
            }
        }),
    })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
        await readAllDriver();
}

let readById = () => {
    let id = ID.value;
    fetch(`http://localhost:8080/driver/read/${id}`)
        .then((data) => {
            if (data.status !== 200) {
                console.error(`status: ${data.status}`);
                return;
            }
            return data.json();
        })
        .then((objectData) => {
            console.log(objectData);
            let tableData = "";
            tableData = `<tr>
      <td>${values.id}</td>
      <td>${values.first_name}</td>
      <td>${values.surname}</td>
      <td>${values.age}</td>
      <td>${values.nationality}</td>
      <td>${values.race_wins}</td>
      <td>${values.best_championship_position}</td>
      </tr>`

            document.getElementById("u_table").innerHTML = objectData;
        })
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

// Update Function 
async function updateDriver() {
    let id = ID.value
     await fetch(`http://localhost:8080/driver/update/${id}`, {
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
        await readById();
}

// Delete Function 
async function deleteDriver() {
    let id = ID.value
    let msg = `Please confirm that you wish to delete your selected entries.`
    if (confirm(msg)) {
        let response = await fetch(`http://localhost:8080/driver/delete/${id}`,
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
    let d = readById();
    return c && d;
}

function deleteAndRead(){
    let e = deleteDriver();
    let f = readAllDriver();
}

createButt.onclick = () => createAndRead();
read.onclick = () => readAllDriver();
