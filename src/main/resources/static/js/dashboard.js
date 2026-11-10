// vacancies growth chart
const ctx = document.getElementById('vacanciesChart');
const vacanciesChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: [],
        datasets: [{
            label: 'Vacancies',
            data: [],
            borderWidth: 2,
        }]
    }
});

// vacancy types chart
const ptx = document.getElementById('vacancyTypes');
const vacancyTypesChart = new Chart(ptx, {
    type: 'pie',
    data: {
        labels: [],
        datasets: [{
            label: 'Vacancies',
            data: [],
            backgroundColor: [
                '#4E52E8',
                '#458BF9',
                '#4BBF88',
                '#FA8E44',
            ],
            borderWidth: 2
        }]
    }
});

async function updateChart(days) {
    const growth = await fetch(`/dashboard/growth/${days}`)
        .then(res => res.json());

    const types = await fetch(`/dashboard/types/${days}`)
        .then(res => res.json());

    vacanciesChart.data.labels = growth.map(it => it.date)
    vacanciesChart.data.datasets[0].data = growth.map(it => it.countVacancies)

    vacancyTypesChart.data.labels = types.map(it => it.vacancyType.charAt(0).toUpperCase() + it.vacancyType.toLowerCase().slice(1).replaceAll('_','-'));
    vacancyTypesChart.data.datasets[0].data = types.map(it => it.countVacancies)

    vacanciesChart.update();
    vacancyTypesChart.update();
}

document.getElementById('days').addEventListener('change', async (event) => {
    const days = event.target.value;
    await updateChart(days);
});

updateChart(document.getElementById('days').value);