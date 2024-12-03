/* script.js */

document.addEventListener('DOMContentLoaded', function() {
    fetchUsers();

    fetch('/games')
        .then(response => response.json())
        .then(data => {
            const gamesContainer = document.querySelector('.games');
            gamesContainer.innerHTML = data.map(game => `
                <div class="game-list">
                    <img src="${game.imageUrl}" alt="${game.name}" style="width:100px; height: 100px;"/>
                    <h3>${game.name}</h3>
                </div>
            `).join('');
        })
        .catch(error => console.error('Error fetching games:', error));

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
