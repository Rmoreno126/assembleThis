/* script.js */

document.addEventListener('DOMContentLoaded', function() {
    fetchUsers();
    fetchGames();

    fetch('/store')
        .then(response => response.json())
        .then(data => {
            const storeContainer = document.querySelector('.game-store');
            storeContainer.innerHTML = data.map(item => `
                <div class="game-list">
                    <img src="${item.imageUrl}" alt="${item.name}" style="width:100px; height: 100px;"/>
                    <h3>${item.name}</h3>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error fetching store items:', error));

    fetch('/events')
        .then(response => response.json())
        .then(data => {
            const eventsContainer = document.querySelector('.event-hero');
            eventsContainer.innerHTML = data.map(event => `
                <div class="event-list">
                    <img src="${event.imageUrl}" alt="${event.title}" style="width:100px; height: 100px;"/>
                    <h3>${event.title}</h3>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error fetching events:', error));
});

function fetchGames() {
    fetch('/api/games')
        .then(response => response.json())
        .then(data => {
            const gamesContainer = document.querySelector('.games');
            gamesContainer.innerHTML = data.map(game => `
                <div class="game-list">
                    <img src="${game.imageUrl}" alt="${game.name}" style="width:100px; height: 100px;"/>
                    <h3>${game.name}</h3>
                    <button onclick="deleteGame(${game.id})">Delete</button>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error fetching games:', error));
}

function addGame() {
    const name = document.getElementById('gameName').value;
    const type = document.getElementById('gameType').value;
    const imageUrl = document.getElementById('gameImageUrl').value;

    const game = { name, type, imageUrl };

    fetch('/api/games', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(game)
    })
    .then(response => response.json())
    .then(data => {
        fetchGames();  // Refresh the list of games
        console.log('Game added:', data);
    })
    .catch(error => console.error('Error adding game:', error));
}

function deleteGame(id) {
    fetch(`/api/games/${id}`, {
        method: 'DELETE'
    })
    .then(() => {
        fetchGames();  // Refresh the list of games
        console.log('Game deleted');
    })
    .catch(error => console.error('Error deleting game:', error));
}

async function fetchUsers() {
    try {
        const response = await fetch('/api/users');
        const users = await response.json();
        const userNames = users.map(user => user.name);
        const userEmails = users.map(user => user.email.length);

        const ctx = document.getElementById('userChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: userNames,
                datasets: [{
                    label: '# of Characters in Email',
                    data: userEmails,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    } catch (error) {
        console.error('Error fetching users:', error);
    }
}

