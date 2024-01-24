from flask import Flask, render_template
import pandas as pd

app = Flask(__name__)

# Charger le fichier CSV provenant de Talend
film_affiche_csv = "film_affiche_/_output/film_affiche.csv"
df_talend = pd.read_csv(film_affiche_csv)
df_talend
@app.route('/')
def index():
    # Prendre les 5 premiers films du fichier CSV
    cinq_premiers_films = df_talend.head(5)
    print(cinq_premiers_films)

    return render_template("index.html", films=cinq_premiers_films.to_dict(orient='records'))

if __name__ == '__main__':
    app.run(debug=True)
