export interface Curso {
  id: number;
  nome: string;
  dataInicio: Date;
  dataFim: Date;
  tarefas: Tarefa[];
};
export interface Tarefa {
  id: number;
  descricao: string;
  tempoGasto: string;
  categoria: Categoria;
  curso: Curso;
};
export interface Categoria {
  id: number;
  nome: string;
};
