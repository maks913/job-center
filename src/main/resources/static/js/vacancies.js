// Save vacancy
document.querySelectorAll(".saveVacancy").forEach(form => {
    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const userId = form.querySelector('[name="user_id"]').value;
        const vacancyId = form.querySelector('[name="vacancy_id"]').value;

        try {
            const response = await fetch("/users/save-vacancy", {
                method: "POST",
                body: new URLSearchParams({
                    vacancyId,
                    userId
                })
            });

            if (response.ok) {
                const icon = form.querySelector("i");

                icon.classList.toggle("bi-bookmark-star");
                icon.classList.toggle("bi-bookmark-star-fill");
            } else {
                console.error("Error occurred while saving vacancy.");
            }
        } catch (error) {
            console.error("Request failed:", error);
        }
    });
});


// Send application
document.querySelectorAll(".sentApplication").forEach(form => {
    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const userId = form.querySelector('[name="user_id"]').value;
        const vacancyId = form.querySelector('[name="vacancy_id"]').value;

        try {
            const response = await fetch("/users/sent-application", {
                method: "POST",
                body: new URLSearchParams({
                    vacancyId,
                    userId
                })
            });

            if (response.ok) {
                const icon = form.querySelector("i");

                icon.classList.toggle("bi-send");
                icon.classList.toggle("bi-send-check-fill");
            } else {
                console.error("Error occurred while sending application.");
            }
        } catch (error) {
            console.error("Request failed:", error);
        }
    });
});